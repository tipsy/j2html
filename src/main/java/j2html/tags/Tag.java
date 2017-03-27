package j2html.tags;

import j2html.attributes.Attribute;

import java.util.ArrayList;

public abstract class Tag {

    protected String tag;
    protected ArrayList<Attribute> attributes;
    protected Tag parent;

    protected Tag(String tagType) {
        this.tag = tagType;
        this.attributes = new ArrayList<Attribute>();
    }

    public void setParent(Tag parent) {
        this.parent = parent;
    }

    /**
     * Sets an attribute on an element
     *
     * @param name  the attribute
     * @param value the attribute value
     */
    public boolean setAttribute(String name, String value) {
        if (value == null) {
            return attributes.add(new Attribute(name));
        }
        for (Attribute attribute : attributes) {
            if (attribute.getName().equals(name)) {
                attribute.setValue(value); //update with new value
                return true;
            }
        }
        return attributes.add(new Attribute(name, value));
    }

    public String render() {
        return renderOpenTag() + renderCloseTag();
    }

    @Override
    public String toString() {
        return this.render();
    }

    public String renderOpenTag() {
        String tagAttributes = "";
        for (Attribute attribute : attributes) {
            tagAttributes += attribute.render();
        }
        return "<" + tag + tagAttributes + ">";
    }

    public String renderCloseTag() {
        return "</" + tag + ">";
    }

}
