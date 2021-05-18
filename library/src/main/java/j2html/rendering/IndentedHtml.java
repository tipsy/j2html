package j2html.rendering;

import j2html.Config;
import j2html.utils.Indenter;
import j2html.utils.TextEscaper;

import java.io.IOException;
import java.util.Stack;

/**
 * Composes HTML with lines breaks and indentation between tags and text.
 *
 * @param <T> The type of the Appendable to which HTML will be appended.
 */
public class IndentedHtml<T extends Appendable> implements HtmlBuilder<T> {

    /**
     * Returns an HtmlBuilder that will generate indented HTML using
     * Config defaults.
     *
     * @param out The Appendable to which HTML will be appended.
     * @param <T> The type of the Appendable to which HTML will be appended.
     * @return An HtmlBuilder for indented HTML.
     */
    public static final <T extends Appendable> IndentedHtml<T> into(T out) {
        return new IndentedHtml<>(out, Config.defaults());
    }

    /**
     * Returns an HtmlBuilder that will generate indented HTML using
     * the given Config.
     *
     * @param out    The Appendable to which HTML will be appended.
     * @param config The Config which will specify indentation, text escapement, tag closing, etc.
     * @param <T>    The type of the Appendable to which HTML will be appended.
     * @return An HtmlBuilder for indented HTML.
     */
    public static final <T extends Appendable> IndentedHtml<T> into(T out, Config config) {
        return new IndentedHtml<>(out, config);
    }

    /**
     * Returns an HtmlBuilder that will generate indented HTML in memory using
     * Config defaults.
     *
     * @param <T> The type of the Appendable to which HTML will be appended.
     * @return An HtmlBuilder for indented HTML.
     */
    public static final IndentedHtml<StringBuilder> inMemory() {
        return into(new StringBuilder());
    }

    /**
     * Returns an HtmlBuilder that will generate indented HTML in memory using
     * the given Config.
     *
     * @param config The Config which will specify indentation, text escapement, tag closing, etc.
     * @param <T>    The type of the Appendable to which HTML will be appended.
     * @return An HtmlBuilder for indented HTML.
     */
    public static final IndentedHtml<StringBuilder> inMemory(Config config) {
        return into(new StringBuilder(), config);
    }

    private final T out;
    private final Indenter indenter;
    private final TextEscaper textEscaper;
    private final TagBuilder enclosingElementAttributes;
    private final TagBuilder emptyElementAttributes;

    // Dealing with preformatted elements (pre and textarea) requires
    // that we know what our parent elements are.  To do that we use
    // a stack; adding items as start tags are created, and removing them
    // as those tags are closed.  Determining whether or not we are
    // currently rendering into a preformatted element is as simple as
    // asking if any tags on the stack match a preformatted element name.
    private Stack<String> trace = new Stack<>();

    private IndentedHtml(T out, Config config) {
        this.out = out;
        this.indenter = config.indenter();
        this.textEscaper = config.textEscaper();
        this.enclosingElementAttributes = new IndentedTagBuilder(false);
        this.emptyElementAttributes = new IndentedTagBuilder(config.closeEmptyTags());
    }

    private boolean isContentSelfFormatting() {
        return trace.contains("pre") || trace.contains("textarea");
    }

    private int lvl() {
        return trace.size();
    }

    @Override
    public TagBuilder appendStartTag(String name) throws IOException {
        if (!isContentSelfFormatting()) {
            out.append(indenter.indent(lvl(), ""));
        }

        trace.push(name);

        out.append("<").append(name);
        return enclosingElementAttributes;
    }

    @Override
    public HtmlBuilder<T> appendEndTag(String name) throws IOException {
        if (!name.equals(trace.peek())) {
            throw new RuntimeException("Incorrect element closed: " + name + ".  Expected: " + trace.peek());
        }

        if (!isContentSelfFormatting()) {
            trace.pop();
            out.append(indenter.indent(lvl(), ""));
        } else {
            trace.pop();
        }

        out.append("</").append(name).append(">");

        if (!isContentSelfFormatting()) {
            out.append("\n");
        }

        return this;
    }

    @Override
    public TagBuilder appendEmptyTag(String name) throws IOException {
        if (!isContentSelfFormatting()) {
            out.append(indenter.indent(lvl(), ""));
        }
        out.append("<").append(name);
        return emptyElementAttributes;
    }

    private void appendLines(String txt) throws IOException {
        if (!isContentSelfFormatting()) {
            String[] lines = txt.split("\n");
            for (String line : lines) {
                out.append(indenter.indent(lvl(), line)).append("\n");
            }
        } else {
            out.append(txt);
        }
    }

    @Override
    public HtmlBuilder<T> appendEscapedText(String txt) throws IOException {
        appendLines(textEscaper.escape(txt));
        return this;
    }

    @Override
    public HtmlBuilder<T> appendUnescapedText(String txt) throws IOException {
        appendLines(txt);
        return this;
    }

    @Override
    public T output() {
        return out;
    }

    @Override
    @Deprecated
    public HtmlBuilder append(CharSequence csq) throws IOException {
        out.append(csq);
        return this;
    }

    @Override
    @Deprecated
    public HtmlBuilder append(CharSequence csq, int start, int end) throws IOException {
        out.append(csq, start, end);
        return this;
    }

    @Override
    @Deprecated
    public HtmlBuilder append(char c) throws IOException {
        out.append(c);
        return this;
    }


    private class IndentedTagBuilder implements TagBuilder {

        private final boolean closeTag;

        private IndentedTagBuilder(boolean closeTag) {
            this.closeTag = closeTag;
        }

        @Override
        public TagBuilder appendAttribute(String name, String value) throws IOException {
            out.append(" ")
                .append(name)
                .append("=\"")
                .append(textEscaper.escape(value))
                .append("\"");
            return this;
        }

        @Override
        public TagBuilder appendBooleanAttribute(String name) throws IOException {
            out.append(" ").append(name);
            return this;
        }

        @Override
        public HtmlBuilder completeTag() throws IOException {
            if (closeTag) {
                out.append("/");
            }
            out.append(">");

            if (!isContentSelfFormatting()) {
                out.append("\n");
            }

            return IndentedHtml.this;
        }

        @Override
        @Deprecated
        public TagBuilder append(CharSequence csq) throws IOException {
            out.append(csq);
            return this;
        }

        @Override
        @Deprecated
        public TagBuilder append(CharSequence csq, int start, int end) throws IOException {
            out.append(csq, start, end);
            return this;
        }

        @Override
        @Deprecated
        public TagBuilder append(char c) throws IOException {
            out.append(c);
            return this;
        }
    }
}
