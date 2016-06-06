package j2html.tags;

import j2html.attributes.*;
import java.util.*;

public abstract class Tag<T extends Tag<T>> extends DomContent {

    protected String tagName;
    private ArrayList<Attribute> attributes;

    protected Tag(String tagName) {
        this.tagName = tagName;
        this.attributes = new ArrayList<>();
    }

    String renderOpenTag() {
        StringBuilder sb = new StringBuilder("<").append( tagName );
        for (Attribute attribute : attributes) {
            sb.append( attribute.render() );
        }
        sb.append( ">" );
        return sb.toString();
    }

    String renderCloseTag() {
        return "</" + tagName + ">";
    }

    /**
     * Sets an attribute on an element
     *
     * @param name  the attribute
     * @param value the attribute value
     */
    boolean setAttribute(String name, String value) {
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
     * Convenience methods that call attr with predefined attributes
     *
     * @return itself for easy chaining
     */
    public T isAutoComplete()                                                { return attr(Attr.AUTOCOMPLETE, null); }
    public T isAutoFocus()                                                   { return attr(Attr.AUTOFOCUS, null); }
    public T isHidden()                                                      { return attr(Attr.HIDDEN, null); }
    public T isRequired()                                                    { return attr(Attr.REQUIRED, null); }
    public T withAlt(String alt)                                             { return attr(Attr.ALT, alt); }
    public T withAction(String action)                                       { return attr(Attr.ACTION, action); }
    public T withCharset(String charset)                                     { return attr(Attr.CHARSET, charset); }
    public T withClass(String className)                                     { return attr(Attr.CLASS, className); }
    public T withContent(String content)                                     { return attr(Attr.CONTENT, content); }
    public T withHref(String href)                                           { return attr(Attr.HREF, href); }
    public T withId(String id)                                               { return attr(Attr.ID, id); }
    public T withData(String dataAttr, String value)                         { return attr(Attr.DATA + "-" + dataAttr, value); }
    public T withMethod(String method)                                       { return attr(Attr.METHOD, method); }
    public T withName(String name)                                           { return attr(Attr.NAME, name); }
    public T withPlaceholder(String placeholder)                             { return attr(Attr.PLACEHOLDER, placeholder); }
    public T withTarget(String target)                                       { return attr(Attr.TARGET, target); }
    public T withType(String type)                                           { return attr(Attr.TYPE, type); }
    public T withRel(String rel)                                             { return attr(Attr.REL, rel); }
    public T withRole(String role)                                           { return attr(Attr.ROLE, role); }
    public T withSrc(String src)                                             { return attr(Attr.SRC, src); }
    public T withValue(String value)                                         { return attr(Attr.VALUE, value); }

    public T withCondAutoComplete(boolean condition)                         { return condAttr(condition, Attr.AUTOCOMPLETE, null); }
    public T withCondAutoFocus(boolean condition)                            { return condAttr(condition, Attr.AUTOFOCUS, null); }
    public T withCondHidden(boolean condition)                               { return condAttr(condition, Attr.HIDDEN, null); }
    public T withCondRequired(boolean condition)                             { return condAttr(condition, Attr.REQUIRED, null); }
    public T withCondAlt(boolean condition, String alt)                      { return condAttr(condition, Attr.ALT, alt); }
    public T withCondAction(boolean condition, String action)                { return condAttr(condition, Attr.ACTION, action); }
    public T withCharset(boolean condition, String charset)                  { return condAttr(condition, Attr.CHARSET, charset); }
    public T withCondClass(boolean condition, String className)              { return condAttr(condition, Attr.CLASS, className); }
    public T withCondContent(boolean condition, String content)              { return condAttr(condition, Attr.CONTENT, content); }
    public T withCondHref(boolean condition, String href)                    { return condAttr(condition, Attr.HREF, href); }
    public T withCondId(boolean condition, String id)                        { return condAttr(condition, Attr.ID, id); }
    public T withCondData(boolean condition, String dataAttr, String value)  { return condAttr(condition, Attr.DATA + "-" + dataAttr, value); }
    public T withCondMethod(boolean condition, String method)                { return condAttr(condition, Attr.METHOD, method); }
    public T withCondName(boolean condition, String name)                    { return condAttr(condition, Attr.NAME, name); }
    public T withCondPlaceholder(boolean condition, String placeholder)      { return condAttr(condition, Attr.PLACEHOLDER, placeholder); }
    public T withCondTarget(boolean condition, String target)                { return condAttr(condition, Attr.TARGET, target); }
    public T withCondType(boolean condition, String type)                    { return condAttr(condition, Attr.TYPE, type); }
    public T withCondRel(boolean condition, String rel)                      { return condAttr(condition, Attr.REL, rel); }
    public T withCondSrc(boolean condition, String src)                      { return condAttr(condition, Attr.SRC, src); }
    public T withCondValue(boolean condition, String value)                  { return condAttr(condition, Attr.VALUE, value); }

}
