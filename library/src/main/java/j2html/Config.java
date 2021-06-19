package j2html;

import j2html.utils.CSSMin;
import j2html.utils.EscapeUtil;
import j2html.utils.Indenter;
import j2html.utils.JSMin;
import j2html.utils.Minifier;
import j2html.utils.TextEscaper;

public class Config {

    /**
     * Change this to configure text-escaping
     * For example, to disable escaping, do <code>{@code Config.textEscaper = text -> text;}</code>
     * @deprecated in favor of {@link j2html.rendering.DefaultHtmlBuilder#withTextEscaper(TextEscaper)}
     */
    @Deprecated
    public static TextEscaper textEscaper = EscapeUtil::escape;
    /**
     * Change this to configure css-minification.
     * The default minifier is https://github.com/barryvan/CSSMin
     */
    public static Minifier cssMinifier = CSSMin::compressCss;
    /**
     * Change this to configure js-minification.
     * The default minifier is a simple whitespace/newline stripper
     */
    public static Minifier jsMinifier = JSMin::compressJs;
    /**
     * Change this to configure enable/disable closing empty tags
     * The default is to NOT close them
     * @deprecated in favor of {@link j2html.rendering.DefaultHtmlBuilder#withEmptyTagsClosed(boolean)}
     */
    @Deprecated
    public static boolean closeEmptyTags = false;

    /**
     * Change this to configure indentation when rendering formatted html
     * The default is four spaces
     * @deprecated in favor of {@link j2html.rendering.DefaultHtmlBuilder#withIndenter(Indenter)}
     */
    @Deprecated
    public static Indenter indenter = Indenter.defaults();


    private TextEscaper _textEscaper;
    private Minifier _cssMinifier;
    private Minifier _jsMinifier;
    private boolean _closeEmptyTags;
    private Indenter _indenter;


    private Config(
        TextEscaper _textEscaper,
        Minifier _cssMinifier,
        Minifier _jsMinifier,
        boolean _closeEmptyTags,
        Indenter _indenter
    ) {
        this._textEscaper = _textEscaper;
        this._cssMinifier = _cssMinifier;
        this._jsMinifier = _jsMinifier;
        this._closeEmptyTags = _closeEmptyTags;
        this._indenter = _indenter;
    }

    /**
     * A copy constructor.
     *
     * @param original The Config to copy fields from.
     */
    private Config(Config original) {
        this._textEscaper = original._textEscaper;
        this._cssMinifier = original._cssMinifier;
        this._jsMinifier = original._jsMinifier;
        this._closeEmptyTags = original._closeEmptyTags;
        this._indenter = original._indenter;
    }

    public TextEscaper textEscaper() {
        return _textEscaper;
    }

    public Minifier cssMinifier() {
        return _cssMinifier;
    }

    public Minifier jsMinifier() {
        return _jsMinifier;
    }

    public boolean closeEmptyTags() {
        return _closeEmptyTags;
    }

    public Indenter indenter() {
        return _indenter;
    }

    /**
     * @deprecated in favor of {@link j2html.rendering.DefaultHtmlBuilder#withTextEscaper(TextEscaper)}
     */
    @Deprecated
    public Config withTextEscaper(TextEscaper textEscaper){
        Config copy = new Config(this);
        copy._textEscaper = textEscaper;
        return copy;
    }

    public Config withCssMinifier(Minifier cssMinifier){
        Config copy = new Config(this);
        copy._cssMinifier = cssMinifier;
        return copy;
    }

    public Config withJsMinifier(Minifier jsMinifier){
        Config copy = new Config(this);
        copy._jsMinifier = jsMinifier;
        return copy;
    }

    /**
     * @deprecated in favor of {@link j2html.rendering.DefaultHtmlBuilder#withEmptyTagsClosed(boolean)}
     */
    @Deprecated
    public Config withEmptyTagsClosed(boolean closeEmptyTags){
        Config copy = new Config(this);
        copy._closeEmptyTags = closeEmptyTags;
        return copy;
    }

    /**
     * @deprecated in favor of {@link j2html.rendering.DefaultHtmlBuilder#withIndenter(Indenter)}
     */
    @Deprecated
    public Config withIndenter(Indenter indenter){
        Config copy = new Config(this);
        copy._indenter = indenter;
        return copy;
    }

    private static final Config DEFAULTS = new Config(
        EscapeUtil::escape,
        CSSMin::compressCss,
        JSMin::compressJs,
        false,
        Indenter.defaults()
    );

    public static final Config defaults() {
        return DEFAULTS;
    }

    public static final Config global() {
        return new Config(
            textEscaper,
            cssMinifier,
            jsMinifier,
            closeEmptyTags,
            indenter
        );
    }

}
