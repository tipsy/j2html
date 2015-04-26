package j2html.src.tags;

import j2html.src.attributes.Attr;

import java.util.ArrayList;
import java.util.List;

public class ContainerTag extends Tag {

    public ArrayList<Tag> children;

    public ContainerTag(String tagType) {
        super(tagType);
        this.children = new ArrayList<>();
    }

    /**
     * Appends a tag to the end of this element
     * @param child tag to be appended
     * @return itself for easy chaining
     */
    public ContainerTag with(Tag child) {
        if (this == child) {
            throw new Error("Cannot append a tag to itself.");
        }
        child.setParent(this);
        children.add(child);
        return this;
    }

    /**
     * Appends a list of tags to the end of this element
     * @param children tags to be appended
     * @return itself for easy chaining
     */
    public ContainerTag with(List<Tag> children) {
        if (children != null) {
            children.forEach(this::with);
        }
        return this;
    }

    /**
     * Appends the tags to the end of this element
     * @param children tags to be appended
     * @return itself for easy chaining
     */
    public ContainerTag with(Tag... children) {
        for (Tag aChildren : children) {
            with(aChildren);
        }
        return this;
    }

    public ContainerTag attr(String attribute, String value) {
        setAttribute(attribute, value);
        return this;
    }

    /**
     * Appends a text tag to this element
     * @param text the text to be appended
     * @return itself for easy chaining
     */
    public ContainerTag withText(String text) {
        return with(new Text(text));
    }

    /**
     * Render the tag and its children
     */
    @Override
    public String render() {
        StringBuilder rendered = new StringBuilder(renderOpenTag());
        if (children != null && children.size() > 0) {
            for (Tag child : children) {
                rendered.append(child.render());
            }
        }
        rendered.append(renderCloseTag());
        return rendered.toString();
    }

    @Override
    public String toString() {
        return this.render();
    }

    /** Methods below this point are convenience methods
     *  that call attr with a predefined attribute.
     */

    //TODO: TEST ?
    public ContainerTag isAutoComplete()                          { return attr(Attr.AUTOCOMPLETE, null); }
    public ContainerTag isAutoFocus()                             { return attr(Attr.AUTOFOCUS, null); }
    public ContainerTag isHidden()                                { return attr(Attr.HIDDEN, null); }
    public ContainerTag isRequired()                              { return attr(Attr.REQUIRED, null); }
    public ContainerTag withAlt(String alt)                       { return attr(Attr.ALT, alt); }
    public ContainerTag withAction(String action)                 { return attr(Attr.ACTION, action); }
    public ContainerTag withCharset(String charset)               { return attr(Attr.CHARSET, charset); }
    public ContainerTag withClass(String className)               { return attr(Attr.CLASS, className); }
    public ContainerTag withContent(String content)               { return attr(Attr.CONTENT, content); }
    public ContainerTag withHref(String href)                     { return attr(Attr.HREF, href); }
    public ContainerTag withId(String id)                         { return attr(Attr.ID, id); }
    public ContainerTag withData(String dataAttr, String value)   { return attr(Attr.DATA + "-" + dataAttr, value); }
    public ContainerTag withMethod(String method)                 { return attr(Attr.METHOD, method); }
    public ContainerTag withName(String name)                     { return attr(Attr.NAME, name); }
    public ContainerTag withPlaceholder(String placeholder)       { return attr(Attr.PLACEHOLDER, placeholder); }
    public ContainerTag withType(String type)                     { return attr(Attr.TYPE, type); }
    public ContainerTag withRel(String rel)                       { return attr(Attr.REL, rel); }
    public ContainerTag withSrc(String src)                       { return attr(Attr.SRC, src); }
    public ContainerTag withValue(String value)                   { return attr(Attr.VALUE, value); }

}
