package j2html.tags;

import java.io.IOException;

public abstract class Renderer {
    /**
     * Render the DomContent and its children
     *
     * @return the rendered string
     */
    public final String render() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            render(stringBuilder);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return stringBuilder.toString();
    }

    /**
     * Render the DomContent and its children
     *
     * @return the rendered string
     */
    public abstract void render(Appendable writer) throws IOException;

    @Override
    public String toString() {
        return render();
    }
}
