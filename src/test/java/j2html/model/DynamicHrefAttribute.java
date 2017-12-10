package j2html.model;

import j2html.attributes.Attribute;
import java.io.IOException;

public class DynamicHrefAttribute extends Attribute {

    public DynamicHrefAttribute() {
        super("href");
    }

    @Override
    public void renderModel(Appendable writer, Object model) throws IOException {
        writer.append(" ");
        writer.append(getName());
        writer.append("=\"");
        writer.append(getUrl(model));
        writer.append("\"");
    }

    public String getUrl(Object model) {
        return "/";
    }
}
