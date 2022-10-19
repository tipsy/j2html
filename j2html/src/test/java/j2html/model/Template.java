package j2html.model;

import j2html.rendering.HtmlBuilder;
import j2html.tags.DomContent;
import java.io.IOException;

public abstract class Template<T> extends DomContent {

    @Override
    @SuppressWarnings("unchecked")
    public final void renderModel(Appendable writer, Object model) throws IOException {
        renderTemplate(writer, (T) model);
    }

    public abstract void renderTemplate(Appendable writer, T model) throws IOException;

    @Override
    public <T extends Appendable> T render(HtmlBuilder<T> builder, Object model) throws IOException{
        renderModel(builder.output(), model);
        return builder.output();
    }
}
