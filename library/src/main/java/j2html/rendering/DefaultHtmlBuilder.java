package j2html.rendering;

import j2html.Config;
import j2html.utils.Indenter;
import j2html.utils.TextEscaper;

/**
 * Default entry point for constructing an {@link HtmlBuilder} instance.
 * Examples:
 * 
 * <pre>
 * HtmlTag html = ...
 * Appendable out = ...
 * html.render(DefaultHtmlBuilder.into(out));
 * </pre>
 * 
 * will write the HTML into {@code out} without any line breaks or indentation
 * using the default settings of {@link Config#defaults()}.
 * <p>
 * 
 * <pre>
 * html.render(DefaultHtmlBuilder.indented(true).into(out));
 * </pre>
 * 
 * will write the HTML into {@code out} with line breaks and indentation using
 * the default settings and the indenter {@link Indenter#defaults()}.
 * <p>
 * 
 * <pre>
 * html.render(DefaultHtmlBuilder.indented(true).withIndenter(Indenter.with("\t")).into(out));
 * </pre>
 * 
 * will use the tab character {@code \t} for indentation.
 * <p>
 * A different {@link TextEscaper} can be provided using
 * {@link #withTextEscaper(TextEscaper)}, a different setting for the closing of
 * empty tags can be applied with {@link #withEmptyTagsClosed(boolean)}.
 * <p>
 * There is also a convenience method {@link #inMemory()} for rendering the HTML
 * into a String:
 * 
 * <pre>
 * String text = html.render(DefaultHtmlBuilder.with...().inMemory()).toString();
 * </pre>
 */
public class DefaultHtmlBuilder {

    public static <A extends Appendable> HtmlBuilder<A> into(A out) {
        return new Configurer().into(out);
    }

    public static HtmlBuilder<StringBuilder> inMemory() {
        return into(new StringBuilder());
    }

    public static Configurer indented(boolean indented) {
        return new Configurer().indented(indented);
    }

    public static Configurer withEmptyTagsClosed(boolean closeTags) {
        return new Configurer().withEmptyTagsClosed(closeTags);
    }

    public static Configurer withTextEscaper(TextEscaper textEscaper) {
        return new Configurer().withTextEscaper(textEscaper);
    }

    public static Configurer withIndenter(Indenter indenter) {
        return new Configurer().withIndenter(indenter);
    }

    /**
     * @deprecated will be removed in a future version
     */
    @Deprecated
    public static Configurer withConfig(Config config) {
        return new Configurer(config);
    }

    public static class Configurer {
        private boolean indented;
        private Config config;

        private Configurer() {
            this(Config.defaults());
        }

        private Configurer(Config config) {
            this.config = config;
            this.indented = false;
        }

        @SuppressWarnings("deprecation")
        public <A extends Appendable> HtmlBuilder<A> into(A out) {
            return this.indented ? IndentedHtml.into(out, this.config) : FlatHtml.into(out, this.config);
        }

        public HtmlBuilder<StringBuilder> inMemory() {
            return into(new StringBuilder());
        }

        public Configurer indented(boolean indented) {
            this.indented = indented;
            return this;
        }

        @SuppressWarnings("deprecation")
        public Configurer withEmptyTagsClosed(boolean closeTags) {
            this.config = this.config.withEmptyTagsClosed(closeTags);
            return this;
        }

        @SuppressWarnings("deprecation")
        public Configurer withTextEscaper(TextEscaper textEscaper) {
            this.config = this.config.withTextEscaper(textEscaper);
            return this;
        }

        @SuppressWarnings("deprecation")
        public Configurer withIndenter(Indenter indenter) {
            this.config = this.config.withIndenter(indenter);
            return this;
        }
    }
}
