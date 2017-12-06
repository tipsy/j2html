package j2html.tags;

import j2html.Config;
import j2html.attributes.Attribute;
import java.io.IOException;

public class EmptyTag extends Tag<EmptyTag> {

    public EmptyTag(String tagName) {
        super(tagName);
    }

    @Override
    public void render(Appendable writer) throws IOException {
        renderModel(writer, null);
    }

    @Override
    public void renderModel(Appendable writer, Object model) throws IOException {
        writer.append("<").append(tagName);
        for (Attribute attribute : getAttributes()) {
            attribute.renderModel(writer, model);
        }
        if (Config.closeEmptyTags) {
            writer.append("/");
        }
        writer.append(">");
    }
}
