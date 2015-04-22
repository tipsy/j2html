package j2html.src.tags;

import j2html.src.attributes.Attribute;

public class SimpleTag extends BaseTag {

    protected SimpleTag(String tagType) {
        super(tagType);
    }

    public String render() {
        StringBuilder b = new StringBuilder("<");
        b.append(tag);
        for (Attribute attr : attributes) {
            b.append(attr.render());
        }
        b.append(">");
        return b.toString();
    }

    @Override
    public String toString() {
        return this.render();
    }

}
