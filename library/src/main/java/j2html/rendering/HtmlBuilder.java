package j2html.rendering;

import java.io.IOException;

/**
 * Implementations of HtmlBuilder are wrappers around an
 * Appendable, and support appending HTML-specific character
 * sequences to that Appendable.
 * <p>
 * Note:  HtmlBuilder extends Appendable for compatibility with
 * previous version of this library.  This extension will probably be
 * removed in the future, so avoid relying on the deprecated methods
 * of this interface.
 *
 * @param <T> The type of the Appendable.  Used so that the
 *            same type can be returned to the caller, allowing
 *            for additional work to be done on the Appendable
 *            without the need for manual casting.
 */
public interface HtmlBuilder<T extends Appendable> extends Appendable {

    /**
     * Appends a start tag with the given name to the output. The
     * returned TagBuilder is then used to append attributes
     * and eventually complete the start tag.
     *
     * @param name The name of the start tag.
     * @return An TagBuilder which can append attributes to the start tag.
     * @throws IOException When the Appendable throws an IOException.
     */
    TagBuilder appendStartTag(String name) throws IOException;

    /**
     * Appends an end tag with the given name to the output.
     *
     * @param name The name of the end tag.
     * @return An HtmlBuilder that can continue appending HTML to the output.
     * @throws IOException When the Appendable throws an IOException.
     */
    HtmlBuilder<T> appendEndTag(String name) throws IOException;

    /**
     * Appends an empty tag with the given name to the output. The
     * returned TagBuilder is then used to append attributes
     * and eventually complete the empty tag.
     *
     * @param name The name of the empty tag.
     * @return An TagBuilder which can append attributes to the empty tag.
     * @throws IOException When the Appendable throws an IOException.
     */
    TagBuilder appendEmptyTag(String name) throws IOException;

    /**
     * Appends escaped text to the output.
     *
     * @param txt The text to append.
     * @return An HtmlBuilder that can continue appending HTML to the output.
     * @throws IOException When the Appendable throws an IOException.
     */
    HtmlBuilder<T> appendEscapedText(String txt) throws IOException;

    /**
     * Appends unescaped text to the output.
     *
     * @param txt The text to append.
     * @return An HtmlBuilder that can continue appending HTML to the output.
     * @throws IOException When the Appendable throws an IOException.
     */
    HtmlBuilder<T> appendUnescapedText(String txt) throws IOException;

    /**
     * Returns the Appendable that was being wrapped.
     *
     * @return The original Appendable.
     */
    T output();

    @Override
    @Deprecated
    HtmlBuilder<T> append(CharSequence csq) throws IOException;

    @Override
    @Deprecated
    HtmlBuilder append(CharSequence csq, int start, int end) throws IOException;

    @Override
    @Deprecated
    HtmlBuilder append(char c) throws IOException;
}
