package j2html.tags;

import java.io.IOException;

public interface Renderable {

    /**
     * Create a StringBuilder and use it to render the Renderable and it's
     * children
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
     * Render the Renderable and it's children using the supplied writer
     *
     * @param writer the current writer
     */
    default void render(Appendable writer) throws IOException {
        renderModel(writer, null);
    }

    /**
     * Render the Renderable and it's children using the supplied writer and a model.
     *
     * @param writer the current writer
     * @param model  a model object to provide data for children to render
     */
    void renderModel(Appendable writer, Object model) throws IOException;
}
