package j2html.tags;

import j2html.attributes.*;

public class EmptyTag extends Tag<EmptyTag> {

    public EmptyTag(String tagName) {
        super(tagName);
    }

    public String render() {
        String tagAttributes = "";
        for (Attribute attribute : attributes) {
            tagAttributes += attribute;
        }
        return "<" + tagName + tagAttributes + ">";
    }

}
