package j2html.tags;

import java.io.IOException;

public abstract class Renderer<T> {
    /**
     * Render the DomContent and its children
     *
     * @return the rendered string
     */
    public final String render() {
        return renderModel(null);
    }

    /**
     * Render the DomContent and its children
     *
     * @return the rendered string
     */
    public void render(Appendable writer) throws IOException {
        renderModel(writer, null);
    }

    public String renderModel(T model) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            renderModel(stringBuilder, model);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return stringBuilder.toString();
    }

    public abstract void renderModel(Appendable writer, T model) throws IOException;

    @Override
    public String toString() {
        return render();
    }
}
