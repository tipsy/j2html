package j2html.model;

import static j2html.TagCreator.div;

import java.io.IOException;

import j2html.tags.ContainerTag;

public class Button extends Template<PageModel> {

    private ContainerTag template;

    public Button() {
        // @formatter:off
        template = div().withClass("button").with(div().withClass("button-text").with(new ButtonText()));
        // @formatter:on
    }

    @Override
    public void renderTemplate(Appendable writer, PageModel model) throws IOException {
        template.renderModel(writer, model.getButtonModel().getText());
    }
}

class ButtonText extends Template<String> {

    @Override
    public void renderTemplate(Appendable writer, String model) throws IOException {
        writer.append(model);
    }
}
