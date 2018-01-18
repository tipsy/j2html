package j2html.tags;

import j2html.Config;
import java.io.IOException;

public class Text extends DomContent {

  private String text;

  public Text(String text) {
    this.text = text;
  }

  @Override
  public void render(Appendable writer) throws IOException {
    renderModel(writer, null);
  }

  @Override
  public void renderModel(Appendable writer, Object model) throws IOException {
    writer.append(Config.textEscaper.escape(text));
  }
}
