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

}
