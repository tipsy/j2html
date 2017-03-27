package j2html.tags;

import j2html.attributes.Attr;

import java.util.ArrayList;
import java.util.List;

public class ContainerTag extends Tag {

    public List<Tag> children;

    public ContainerTag(String tagType) {
        super(tagType);
        this.children = new ArrayList<Tag>();
    }

    /**
     * Appends a tag to the end of this element
     *
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
     * Call with-method based on condition
     * {@link #with(Tag child)}
     */
    public ContainerTag condWith(boolean condition, Tag child) {
        return condition ? this.with(child) : this;
    }

    /**
     * Appends a list of tags to the end of this element
     *
     * @param children tags to be appended
     * @return itself for easy chaining
     */
    public ContainerTag with(List<Tag> children) {
        if (children != null) {
            for (Tag child : children) {
                this.with(child);
            }
        }
        return this;
    }

    /**
     * Call with-method based on condition
     * {@link #with(List children)}
     */
    public ContainerTag condWith(boolean condition, List<Tag> children) {
        return condition ? this.with(children) : this;
    }

    /**
     * Appends the tags to the end of this element
     *
     * @param children tags to be appended
     * @return itself for easy chaining
     */
    public ContainerTag with(Tag... children) {
        for (Tag aChildren : children) {
            with(aChildren);
        }
        return this;
    }

    /**
     * Call with-method based on condition
     * {@link #with(Tag... children)}
     */
    public ContainerTag condWith(boolean condition, Tag... children) {
        return condition ? this.with(children) : this;
    }

    /**
     * Appends a text tag to this element
     *
     * @param text the text to be appended
     * @return itself for easy chaining
     */
    public ContainerTag withText(String text) {
        return with(new Text(text));
    }

    /**
     * Sets a custom attribute
     *
     * @param attribute the attribute name
     * @param value     the attribute value
     * @return itself for easy chaining
     */
    public ContainerTag attr(String attribute, String value) {
        setAttribute(attribute, value);
        return this;
    }

    /**
     * Call attr-method based on condition
     * {@link #attr(String attribute, String value)}
     */
    public ContainerTag condAttr(boolean condition, String attribute, String value) {
        return condition ? attr(attribute, value) : this;
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

    /**
     * Methods below this point are convenience methods
     * that call attr with a predefined attribute.
     */

    //TODO: TEST ?
    public ContainerTag isAutoComplete()                                                { return attr(Attr.AUTOCOMPLETE, null); }
    public ContainerTag isAutoFocus()                                                   { return attr(Attr.AUTOFOCUS, null); }
    public ContainerTag isHidden()                                                      { return attr(Attr.HIDDEN, null); }
    public ContainerTag isRequired()                                                    { return attr(Attr.REQUIRED, null); }
    public ContainerTag withAlt(String alt)                                             { return attr(Attr.ALT, alt); }
    public ContainerTag withAction(String action)                                       { return attr(Attr.ACTION, action); }
    public ContainerTag withCharset(String charset)                                     { return attr(Attr.CHARSET, charset); }
    public ContainerTag withClass(String className)                                     { return attr(Attr.CLASS, className); }
    public ContainerTag withContent(String content)                                     { return attr(Attr.CONTENT, content); }
    public ContainerTag withHref(String href)                                           { return attr(Attr.HREF, href); }
    public ContainerTag withId(String id)                                               { return attr(Attr.ID, id); }
    public ContainerTag withData(String dataAttr, String value)                         { return attr(Attr.DATA + "-" + dataAttr, value); }
    public ContainerTag withMethod(String method)                                       { return attr(Attr.METHOD, method); }
    public ContainerTag withName(String name)                                           { return attr(Attr.NAME, name); }
    public ContainerTag withPlaceholder(String placeholder)                             { return attr(Attr.PLACEHOLDER, placeholder); }
    public ContainerTag withTarget(String target)                                       { return attr(Attr.TARGET, target); }
    public ContainerTag withType(String type)                                           { return attr(Attr.TYPE, type); }
    public ContainerTag withRel(String rel)                                             { return attr(Attr.REL, rel); }
    public ContainerTag withSrc(String src)                                             { return attr(Attr.SRC, src); }
    public ContainerTag withValue(String value)                                         { return attr(Attr.VALUE, value); }

    public ContainerTag withCondAutoComplete(boolean condition)                         { return condAttr(condition, Attr.AUTOCOMPLETE, null); }
    public ContainerTag withCondAutoFocus(boolean condition)                            { return condAttr(condition, Attr.AUTOFOCUS, null); }
    public ContainerTag withCondHidden(boolean condition)                               { return condAttr(condition, Attr.HIDDEN, null); }
    public ContainerTag withCondRequired(boolean condition)                             { return condAttr(condition, Attr.REQUIRED, null); }
    public ContainerTag withCondAlt(boolean condition, String alt)                      { return condAttr(condition, Attr.ALT, alt); }
    public ContainerTag withCondAction(boolean condition, String action)                { return condAttr(condition, Attr.ACTION, action); }
    public ContainerTag withCharset(boolean condition, String charset)                  { return condAttr(condition, Attr.CHARSET, charset); }
    public ContainerTag withCondClass(boolean condition, String className)              { return condAttr(condition, Attr.CLASS, className); }
    public ContainerTag withCondContent(boolean condition, String content)              { return condAttr(condition, Attr.CONTENT, content); }
    public ContainerTag withCondHref(boolean condition, String href)                    { return condAttr(condition, Attr.HREF, href); }
    public ContainerTag withCondId(boolean condition, String id)                        { return condAttr(condition, Attr.ID, id); }
    public ContainerTag withCondData(boolean condition, String dataAttr, String value)  { return condAttr(condition, Attr.DATA + "-" + dataAttr, value); }
    public ContainerTag withCondMethod(boolean condition, String method)                { return condAttr(condition, Attr.METHOD, method); }
    public ContainerTag withCondName(boolean condition, String name)                    { return condAttr(condition, Attr.NAME, name); }
    public ContainerTag withCondPlaceholder(boolean condition, String placeholder)      { return condAttr(condition, Attr.PLACEHOLDER, placeholder); }
    public ContainerTag withCondTarget(boolean condition, String target)                { return condAttr(condition, Attr.TARGET, target); }
    public ContainerTag withCondType(boolean condition, String type)                    { return condAttr(condition, Attr.TYPE, type); }
    public ContainerTag withCondRel(boolean condition, String rel)                      { return condAttr(condition, Attr.REL, rel); }
    public ContainerTag withCondSrc(boolean condition, String src)                      { return condAttr(condition, Attr.SRC, src); }
    public ContainerTag withCondValue(boolean condition, String value)                  { return condAttr(condition, Attr.VALUE, value); }

}
