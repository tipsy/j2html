package j2html.rendering;

import j2html.Config;
import j2html.utils.TextEscaper;

import java.io.IOException;

/**
 * Composes HTML without any extra line breaks or indentation.
 *
 * @param <T> The type of the Appendable to which HTML will be appended.
 */
public class FlatHtml<T extends Appendable> implements HtmlBuilder<T> {

    /**
     * Returns an HtmlBuilder that will generate flat HTML using
     * Config defaults.
     *
     * @param out The Appendable to which HTML will be appended.
     * @param <T> The type of the Appendable to which HTML will be appended.
     * @return An HtmlBuilder for flat HTML.
     */
    public static final <T extends Appendable> FlatHtml<T> into(T out) {
        return new FlatHtml<>(out, Config.defaults());
    }

    /**
     * Returns an HtmlBuilder that will generate flat HTML using
     * the given Config.
     *
     * @param out    The Appendable to which HTML will be appended.
     * @param config The Config which will specify text escapement, tag closing, etc.
     * @param <T>    The type of the Appendable to which HTML will be appended.
     * @return An HtmlBuilder for flat HTML.
     */
    public static final <T extends Appendable> FlatHtml<T> into(T out, Config config) {
        return new FlatHtml<>(out, config);
    }

    /**
     * Returns an HtmlBuilder that will generate flat HTML in memory
     * using Config defaults.
     *
     * @return An HtmlBuilder for flat HTML.
     */
    public static final FlatHtml<StringBuilder> inMemory() {
        return into(new StringBuilder());
    }

    /**
     * Returns an HtmlBuilder that will generate flat HTML in memory
     * using the given Config.
     * @param config The Config which will specify text escapement, tag closing, etc.
     * @return An HtmlBuilder for flat HTML.
     */
    public static final FlatHtml<StringBuilder> inMemory(Config config) {
        return into(new StringBuilder(), config);
    }

    private final T out;
    private final TextEscaper textEscaper;
    private final TagBuilder enclosingElementAttributes;
    private final TagBuilder emptyElementAttributes;

    private FlatHtml(T out, Config config) {
        this.out = out;
        this.textEscaper = config.textEscaper();
        this.enclosingElementAttributes = new FlatTagBuilder(false);
        this.emptyElementAttributes = new FlatTagBuilder(config.closeEmptyTags());
    }

    public T output() {
        return out;
    }

    @Override
    @Deprecated
    public HtmlBuilder<T> append(CharSequence csq) throws IOException {
        out.append(csq);
        return this;
    }

    @Override
    @Deprecated
    public HtmlBuilder<T> append(CharSequence csq, int start, int end) throws IOException {
        out.append(csq, start, end);
        return this;
    }

    @Override
    @Deprecated
    public HtmlBuilder<T> append(char c) throws IOException {
        out.append(c);
        return this;
    }

    @Override
    public TagBuilder appendStartTag(String name) throws IOException {
        out.append("<").append(name);
        return enclosingElementAttributes;
    }

    @Override
    public HtmlBuilder<T> appendEndTag(String name) throws IOException {
        out.append("</").append(name).append(">");
        return this;
    }

    @Override
    public TagBuilder appendEmptyTag(String name) throws IOException {
        out.append("<").append(name);
        return emptyElementAttributes;
    }

    @Override
    public HtmlBuilder<T> appendEscapedText(String txt) throws IOException {
        out.append(textEscaper.escape(txt));
        return this;
    }

    @Override
    public HtmlBuilder<T> appendUnescapedText(String txt) throws IOException {
        out.append(txt);
        return this;
    }

    private class FlatTagBuilder implements TagBuilder {

        private final boolean closeTag;

        private FlatTagBuilder(boolean closeTag) {
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

            return FlatHtml.this;
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
