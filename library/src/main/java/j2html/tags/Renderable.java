package j2html.tags;

import java.io.IOException;

public interface Renderable {

    /**
     * Render the Renderable and its children as a string.
     */
    default String render() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            render(stringBuilder);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return stringBuilder.toString();
    }

    /**
     * Render the Renderable and its children using the supplied {@code writer}.
     *
     * @param writer the current writer
     */
    default void render(Appendable writer) throws IOException {
        renderModel(writer, null);
    }

    /**
     * Render the Renderable and its children using the supplied {@code writer} and
     * a {@code model}.
     *
     * @param writer the current writer
     * @param model  a model object to provide data for children to render
     */
    void renderModel(Appendable writer, Object model) throws IOException;

    /**
     * Render the Renderable and its children as a string, adding newlines before
     * each child and using {@link j2html.Config#indenter} to indent a child based
     * on how deep in the tree it is
     */
    default String renderFormatted() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            renderFormatted(stringBuilder);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return stringBuilder.toString();
    }

    /**
     * Render the Renderable and its children using the supplier {@code writer},
     * adding newlines before each child and using {@link j2html.Config#indenter} to
     * indent a child based on how deep in the tree it is
     *
     * @param writer the current writer
     */
    default void renderFormatted(Appendable writer) throws IOException {
        render(writer); // to be overridden by subclasses
    }
}
