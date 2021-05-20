package j2html.rendering;

import java.io.IOException;

/**
 * Implementations of TagBuilder are used to append HTML tag
 * attributes to an Appendable.  TagBuilders are scoped to the
 * creation and completion of a specific tag, and should not be used
 * outside of that tag.
 * <p>
 * Note:  TagBuilder extends Appendable for compatibility with
 * previous version of this library.  This extension will probably be
 * removed in the future, so avoid relying on the deprecated methods
 * of this interface.
 */
public interface TagBuilder extends Appendable {

    /**
     * Appends an key/value pair as an HTML attribute to the current tag.
     *
     * @param name  The name of an attribute.
     * @param value The value of an attribute.
     * @return An TagBuilder which can continue appending attributes.
     * @throws IOException When the Appendable throws an IOException.
     */
    TagBuilder appendAttribute(String name, String value) throws IOException;

    /**
     * Appends a name, as a boolean HTML attribute to the current tag.
     *
     * @param name The name of the boolean attribute.
     * @return An TagBuilder which can continue appending attributes.
     * @throws IOException When the Appendable throws an IOException.
     */
    TagBuilder appendBooleanAttribute(String name) throws IOException;

    /**
     * Appends any characters which are necessary to close the current tag,
     * and returns an HtmlBuilder that can continue appending to the output.
     *
     * @return An HtmlBuilder that can continue appending HTML to the output.
     * @throws IOException When the Appendable throws an IOException.
     */
    HtmlBuilder<? extends Appendable> completeTag() throws IOException;

    @Override
    @Deprecated
    TagBuilder append(CharSequence csq) throws IOException;

    @Override
    @Deprecated
    TagBuilder append(CharSequence csq, int start, int end) throws IOException;

    @Override
    @Deprecated
    TagBuilder append(char c) throws IOException;
}
