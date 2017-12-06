package j2html.model;

import java.io.IOException;

import j2html.tags.DomContent;

public abstract class Template<T> extends DomContent {

    @Override
    @SuppressWarnings("unchecked")
    public final void renderModel(Appendable writer, Object model) throws IOException {
        renderTemplate(writer, (T) model);
    }

    public abstract void renderTemplate(Appendable writer, T model) throws IOException;
}
