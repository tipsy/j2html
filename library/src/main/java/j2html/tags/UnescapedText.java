package j2html.tags;

import j2html.Config;
import j2html.rendering.DefaultHtmlBuilder;
import j2html.rendering.HtmlBuilder;
import java.io.IOException;

public class UnescapedText extends DomContent {

    private final String text;

    public UnescapedText(String text) {
        this.text = text;
    }

    @Override
    public <T extends Appendable> T render(HtmlBuilder<T> builder, Object model) throws IOException {
        builder.appendUnescapedText(String.valueOf(text));
        return builder.output();
    }

    @Override
    @Deprecated
    public void renderModel(Appendable writer, Object model) throws IOException {
        HtmlBuilder<?> builder = (writer instanceof HtmlBuilder)
            ? (HtmlBuilder<?>) writer
            : DefaultHtmlBuilder.withConfig(Config.global()).into(writer);

        render(builder, model);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof UnescapedText)) {
            return false;
        }
        return ((UnescapedText) obj).render().equals(this.render());
    }
}
