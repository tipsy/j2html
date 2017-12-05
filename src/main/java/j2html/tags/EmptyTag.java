package j2html.tags;

import java.io.IOException;

import j2html.Config;
import j2html.attributes.Attribute;

public class EmptyTag extends Tag<EmptyTag> {

    public EmptyTag(String tagName) {
        super(tagName);
    }

    @Override
    public void render(Appendable writer) throws IOException {
        writer.append("<").append(tagName);
        for (Attribute attribute : getAttributes()) {
            attribute.render(writer);
        }
        if (Config.closeEmptyTags) {
            writer.append("/");
        }
        writer.append(">");
    }
}
