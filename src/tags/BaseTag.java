package javaHtmlGenerator.tags;

import java.util.ArrayList;
import javaHtmlGenerator.attributes.Attribute;

public abstract class BaseTag {

    protected String tag;
    protected ArrayList<Attribute> attributes;
    protected BaseTag parent;

    protected BaseTag(String tagType) {
        this.tag = tagType;
        this.attributes = new ArrayList<Attribute>();
    }

    public void setParent(BaseTag parent) {
        this.parent = parent;
    }

    /**
     * Sets an attribute on an element
     * @param name  the attribute
     * @param value the attribute value
     */
    public void setAttribute(String name, String value) {
        if (value != null) {
            for (Attribute attribute : attributes) {
                if (attribute.getName().equals(name)) {
                    attribute.setValue(value);
                    return;
                }
            }
            attributes.add(new Attribute(name, value));
        }
    }

    public String render() {
        return openTag() + closeTag();
    }

    @Override
    public String toString() {
        return this.render();
    }

    protected String openTag() {
        StringBuilder b = new StringBuilder("<");
        b.append(tag);
        for (Attribute attr : attributes) {
            b.append(attr.render());
        }
        b.append(">");
        return b.toString();
    }

    protected String closeTag() {
        return "</" + tag + ">";
    }

}
