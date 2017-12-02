package j2html.model;

import static j2html.TagCreator.div;

import java.io.IOException;

import j2html.tags.ContainerTag;
import j2html.tags.DomContent;

public class Button extends DomContent<PageModel> {

    private ButtonDomContent buttonDomContent = new ButtonDomContent();

    @Override
    public void renderModel(Appendable writer, PageModel model) throws IOException {
        buttonDomContent.renderModel(writer, model.getButtonModel());
    }
}

class ButtonDomContent extends DomContent<ButtonModel> {

    private ContainerTag template;

    public ButtonDomContent() {
        // @formatter:off
        template =
                div()
                .withClass("button")
                .with(
                        div()
                        .withClass("button-text")
                        .with(
                              new ButtonText()
                        )
                );
        // @formatter:on
    }
    @Override
    public void renderModel(Appendable writer, ButtonModel model) throws IOException {
        template.renderModel(writer, model.getText());
    }
}

class ButtonText extends DomContent<String> {

    @Override
    public void renderModel(Appendable writer, String model) throws IOException {
        writer.append(model);
    }

}