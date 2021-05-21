package j2html.tags;

import j2html.Config;
import j2html.rendering.FlatHtml;
import j2html.rendering.HtmlBuilder;

import java.io.IOException;

public class Text extends DomContent {

    private final String text;

    public Text(String text) {
        this.text = text;
    }

    @Override
    public <T extends Appendable> T render(HtmlBuilder<T> builder, Object model) throws IOException {
        builder.appendEscapedText(String.valueOf(text));
        return builder.output();
    }

    @Override
    @Deprecated
    public void renderModel(Appendable writer, Object model) throws IOException {
        HtmlBuilder<?> builder = (writer instanceof HtmlBuilder)
            ? (HtmlBuilder<?>) writer
            : FlatHtml.into(writer, Config.global());

        render(builder, model);
    }

}
