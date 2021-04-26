package j2html.tags;

import java.io.IOException;

public class UnescapedText extends DomContent {

    private String text;

    public UnescapedText(String text) {
        this.text = text;
    }

    @Override
    public void render(Appendable writer) throws IOException {
        renderModel(writer, null);
    }

    @Override
    public void renderModel(Appendable writer, Object model) throws IOException {
        writer.append(text);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof UnescapedText)) {
            return false;
        }
        return ((UnescapedText) obj).render().equals(this.render());
    }
}
