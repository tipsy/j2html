package j2html.tags;

import j2html.attributes.*;
import java.util.*;

public abstract class Tag<T> extends DomContent {

    String tagName;
    ArrayList<Attribute> attributes;

    Tag(String tagName) {
        this.tagName = tagName;
        this.attributes = new ArrayList<>();
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

    String renderOpenTag() {
        String tagAttributes = "";
        for (Attribute attribute : attributes) {
            tagAttributes += attribute.render();
        }
        return "<" + tagName + tagAttributes + ">";
    }

    String renderCloseTag() {
        return "</" + tagName + ">";
    }

    /**
     * Sets a custom attribute
     *
     * @param attribute the attribute name
     * @param value     the attribute value
     * @return itself for easy chaining
     */
    public T attr(String attribute, String value) {
        setAttribute(attribute, value);
        return (T) this;
    }

    /**
     * Call attr-method based on condition
     * {@link #attr(String attribute, String value)}
     */
    public T condAttr(boolean condition, String attribute, String value) {
        return (condition ? attr(attribute, value) : (T) this);
    }

    /**
     * Convenience methods calling attr/cond-attr
     * @return itself for easy chaining
     */
    T isAutoComplete()                                                { return attr(Attr.AUTOCOMPLETE, null); }
    T isAutoFocus()                                                   { return attr(Attr.AUTOFOCUS, null); }
    T isHidden()                                                      { return attr(Attr.HIDDEN, null); }
    T isRequired()                                                    { return attr(Attr.REQUIRED, null); }
    T withAlt(String alt)                                             { return attr(Attr.ALT, alt); }
    T withAction(String action)                                       { return attr(Attr.ACTION, action); }
    T withCharset(String charset)                                     { return attr(Attr.CHARSET, charset); }
    T withClass(String className)                                     { return attr(Attr.CLASS, className); }
    T withContent(String content)                                     { return attr(Attr.CONTENT, content); }
    T withHref(String href)                                           { return attr(Attr.HREF, href); }
    T withId(String id)                                               { return attr(Attr.ID, id); }
    T withData(String dataAttr, String value)                         { return attr(Attr.DATA + "-" + dataAttr, value); }
    T withMethod(String method)                                       { return attr(Attr.METHOD, method); }
    T withName(String name)                                           { return attr(Attr.NAME, name); }
    T withPlaceholder(String placeholder)                             { return attr(Attr.PLACEHOLDER, placeholder); }
    T withTarget(String target)                                       { return attr(Attr.TARGET, target); }
    T withType(String type)                                           { return attr(Attr.TYPE, type); }
    T withRel(String rel)                                             { return attr(Attr.REL, rel); }
    T withRole(String role)                                           { return attr(Attr.ROLE, role); }
    T withSrc(String src)                                             { return attr(Attr.SRC, src); }
    T withValue(String value)                                         { return attr(Attr.VALUE, value); }

    T withCondAutoComplete(boolean condition)                         { return condAttr(condition, Attr.AUTOCOMPLETE, null); }
    T withCondAutoFocus(boolean condition)                            { return condAttr(condition, Attr.AUTOFOCUS, null); }
    T withCondHidden(boolean condition)                               { return condAttr(condition, Attr.HIDDEN, null); }
    T withCondRequired(boolean condition)                             { return condAttr(condition, Attr.REQUIRED, null); }
    T withCondAlt(boolean condition, String alt)                      { return condAttr(condition, Attr.ALT, alt); }
    T withCondAction(boolean condition, String action)                { return condAttr(condition, Attr.ACTION, action); }
    T withCharset(boolean condition, String charset)                  { return condAttr(condition, Attr.CHARSET, charset); }
    T withCondClass(boolean condition, String className)              { return condAttr(condition, Attr.CLASS, className); }
    T withCondContent(boolean condition, String content)              { return condAttr(condition, Attr.CONTENT, content); }
    T withCondHref(boolean condition, String href)                    { return condAttr(condition, Attr.HREF, href); }
    T withCondId(boolean condition, String id)                        { return condAttr(condition, Attr.ID, id); }
    T withCondData(boolean condition, String dataAttr, String value)  { return condAttr(condition, Attr.DATA + "-" + dataAttr, value); }
    T withCondMethod(boolean condition, String method)                { return condAttr(condition, Attr.METHOD, method); }
    T withCondName(boolean condition, String name)                    { return condAttr(condition, Attr.NAME, name); }
    T withCondPlaceholder(boolean condition, String placeholder)      { return condAttr(condition, Attr.PLACEHOLDER, placeholder); }
    T withCondTarget(boolean condition, String target)                { return condAttr(condition, Attr.TARGET, target); }
    T withCondType(boolean condition, String type)                    { return condAttr(condition, Attr.TYPE, type); }
    T withCondRel(boolean condition, String rel)                      { return condAttr(condition, Attr.REL, rel); }
    T withCondSrc(boolean condition, String src)                      { return condAttr(condition, Attr.SRC, src); }
    T withCondValue(boolean condition, String value)                  { return condAttr(condition, Attr.VALUE, value); }

}
