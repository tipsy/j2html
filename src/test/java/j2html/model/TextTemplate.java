package j2html.model;

import java.io.IOException;

import j2html.tags.DomContent;

public class TextTemplate extends DomContent<PageModel> {

    @Override
    public void renderModel(Appendable writer, PageModel pageModel) throws IOException {
        writer.append(pageModel.getText());
    }
}
