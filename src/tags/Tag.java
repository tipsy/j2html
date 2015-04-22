package j2html.src.tags;

import j2html.src.attributes.Attr;

import java.util.ArrayList;
import java.util.List;

public class Tag extends BaseTag {

    public ArrayList<BaseTag> children;

    public Tag(String tagType) {
        super(tagType);
        this.children = new ArrayList<>();
    }

    /**
     * Appends a node to the end of this element
     * @param child node to be appended
     * @return itself for easy chaining
     */
    public Tag with(BaseTag child) {
        if (this == child) {
            throw new Error("Cannot append a node to itself.");
        }
        child.setParent(this);
        children.add(child);
        return this;
    }

    /**
     * Appends a list of nodes to the end of this element
     * @param children nodes to be appended
     * @return itself for easy chaining
     */
    public Tag with(List<BaseTag> children) {
        if (children != null) {
            children.forEach(this::with);
        }
        return this;
    }

    /**
     * Appends the nodes to the end of this element
     * @param children nodes to be appended
     * @return itself for easy chaining
     */
    public Tag with(BaseTag... children) {
        for (BaseTag aChildren : children) {
            with(aChildren);
        }
        return this;
    }

    public Tag attr(String attribute, String value) {
        setAttribute(attribute, value);
        return this;
    }

    /**
     * Appends a text node to this element
     * @param text the text to be appended
     * @return itself for easy chaining
     */
    public Tag withText(String text) {
        return with(new Text(text));
    }

    /**
     * Render the node and its children
     */
    @Override
    public String render() {
        StringBuilder b = new StringBuilder(openTag());
        if (children != null && children.size() > 0) {
            for (BaseTag child : children) {
                b.append(child.render());
            }
        }
        b.append(closeTag());
        return b.toString();
    }

    @Override
    public String toString() {
        return this.render();
    }

    /** Methods below this point are convenience methods
     *  that call attr with a predefined attribute.
     */

    public Tag withId(String id) {
        return attr(Attr.ID, id);
    }

    public Tag withClass(String className) {
        return attr(Attr.CLASS, className);
    }

    public Tag withType(String type) {
        return attr(Attr.TYPE, type);
    }

    public Tag withName(String name) {
        return attr(Attr.NAME, name);
    }

    public Tag withPlaceholder(String placeholder) {
        return attr(Attr.PLACEHOLDER, placeholder);
    }

    public Tag isRequired() {
        return attr(Attr.REQUIRED, null);
    }

    public Tag withRel(String rel) {
        return attr(Attr.REL, rel);
    }

    public Tag withHref(String href) {
        return attr(Attr.HREF, href);
    }

    public Tag withSrc(String src) {
        return attr(Attr.SRC, src);
    }

    public Tag withMethod(String method) {
        return attr(Attr.METHOD, method);
    }


}
