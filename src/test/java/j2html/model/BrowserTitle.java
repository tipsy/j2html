package j2html.model;

import static j2html.TagCreator.title;

import java.io.IOException;

import j2html.tags.DomContent;

public class BrowserTitle extends DomContent<PageModel> {

    @Override
    public void renderModel(Appendable writer, PageModel model) throws IOException {
        title(model.getTitle()).renderModel(writer, model);
    }
}
