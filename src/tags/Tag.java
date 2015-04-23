package j2html.src.tags;

import j2html.src.attributes.Attribute;

import java.util.ArrayList;

public abstract class Tag {

    protected String tag;
    protected ArrayList<Attribute> attributes;
    protected Tag parent;

    protected Tag(String tagType) {
        this.tag = tagType;
        this.attributes = new ArrayList<>();
    }

    public void setParent(Tag parent) {
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
                    //if attribute exists we set the attribute value in stead of just adding a new attribute
                    attribute.setValue(value);
                    return;
                }
            }
            attributes.add(new Attribute(name, value));
        } else {
            attributes.add(new Attribute(name));
        }
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
