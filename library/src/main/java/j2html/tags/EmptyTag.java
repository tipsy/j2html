package j2html.tags;

import j2html.Config;
import j2html.attributes.Attribute;
import java.io.IOException;

public class EmptyTag<T extends EmptyTag<T>> extends Tag<T> {

    public EmptyTag(String tagName) {
        super(tagName);
        if(tagName == null){
            throw new IllegalArgumentException("Illegal tag name: null");
        }
        if("".equals(tagName)){
            throw new IllegalArgumentException("Illegal tag name: \"\"");
        }
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
