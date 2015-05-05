package j2html.src.tags;

import j2html.src.attributes.Attr;
import j2html.src.attributes.Attribute;

public class EmptyTag extends Tag {

    protected EmptyTag(String tagType) {
        super(tagType);
    }

    /**
     * Sets a custom attribute
     * @param attribute the attribute name
     * @param value the attribute value
     * @return itself for easy chaining
     */
    public EmptyTag attr(String attribute, String value) {
        setAttribute(attribute, value);
        return this;
    }

    /**
     * Sets a custom attribute based on a condition
     * @param condition the condition that decides if attribute should be set
     * @param attribute the attribute name
     * @param value the attribute value
     * @return itself for easy chaining
     */
    public EmptyTag condAttr(boolean condition, String attribute, String value) {
        return condition ? attr(attribute, value) : this;
    }

    public String render() {
        String tagAttributes = "";
        for (Attribute attribute : attributes) {
            tagAttributes += attribute;
        }
        return "<"+tag+tagAttributes+">";
    }

    @Override
    public String toString() {
        return this.render();
    }


    /** Methods below this point are convenience methods
     *  that call attr with a predefined attribute.
     */

    //TODO: TEST ?
    public EmptyTag isAutoComplete()                          { return attr(Attr.AUTOCOMPLETE, null); }
    public EmptyTag isAutoFocus()                             { return attr(Attr.AUTOFOCUS, null); }
    public EmptyTag isHidden()                                { return attr(Attr.HIDDEN, null); }
    public EmptyTag isRequired()                              { return attr(Attr.REQUIRED, null); }
    public EmptyTag withAlt(String alt)                       { return attr(Attr.ALT, alt); }
    public EmptyTag withAction(String action)                 { return attr(Attr.ACTION, action); }
    public EmptyTag withCharset(String charset)               { return attr(Attr.CHARSET, charset); }
    public EmptyTag withClass(String className)               { return attr(Attr.CLASS, className); }
    public EmptyTag withContent(String content)               { return attr(Attr.CONTENT, content); }
    public EmptyTag withHref(String href)                     { return attr(Attr.HREF, href); }
    public EmptyTag withId(String id)                         { return attr(Attr.ID, id); }
    public EmptyTag withData(String dataAttr, String value)   { return attr(Attr.DATA + "-" + dataAttr, value); }
    public EmptyTag withMethod(String method)                 { return attr(Attr.METHOD, method); }
    public EmptyTag withName(String name)                     { return attr(Attr.NAME, name); }
    public EmptyTag withPlaceholder(String placeholder)       { return attr(Attr.PLACEHOLDER, placeholder); }
    public EmptyTag withTarget(String target)                 { return attr(Attr.TARGET, target); }
    public EmptyTag withType(String type)                     { return attr(Attr.TYPE, type); }
    public EmptyTag withRel(String rel)                       { return attr(Attr.REL, rel); }
    public EmptyTag withSrc(String src)                       { return attr(Attr.SRC, src); }
    public EmptyTag withValue(String value)                   { return attr(Attr.VALUE, value); }

    public EmptyTag withCondAutoComplete(boolean condition)                         { return condAttr(condition, Attr.AUTOCOMPLETE, null); }
    public EmptyTag withCondAutoFocus(boolean condition)                            { return condAttr(condition, Attr.AUTOFOCUS, null); }
    public EmptyTag withCondHidden(boolean condition)                               { return condAttr(condition, Attr.HIDDEN, null); }
    public EmptyTag withCondRequired(boolean condition)                             { return condAttr(condition, Attr.REQUIRED, null); }
    public EmptyTag withCondAlt(boolean condition, String alt)                      { return condAttr(condition, Attr.ALT, alt); }
    public EmptyTag withCondAction(boolean condition, String action)                { return condAttr(condition, Attr.ACTION, action); }
    public EmptyTag withCharset(boolean condition, String charset)                  { return condAttr(condition, Attr.CHARSET, charset); }
    public EmptyTag withCondClass(boolean condition, String className)              { return condAttr(condition, Attr.CLASS, className); }
    public EmptyTag withCondContent(boolean condition, String content)              { return condAttr(condition, Attr.CONTENT, content); }
    public EmptyTag withCondHref(boolean condition, String href)                    { return condAttr(condition, Attr.HREF, href); }
    public EmptyTag withCondId(boolean condition, String id)                        { return condAttr(condition, Attr.ID, id); }
    public EmptyTag withCondData(boolean condition, String dataAttr, String value)  { return condAttr(condition, Attr.DATA + "-" + dataAttr, value); }
    public EmptyTag withCondMethod(boolean condition, String method)                { return condAttr(condition, Attr.METHOD, method); }
    public EmptyTag withCondName(boolean condition, String name)                    { return condAttr(condition, Attr.NAME, name); }
    public EmptyTag withCondPlaceholder(boolean condition, String placeholder)      { return condAttr(condition, Attr.PLACEHOLDER, placeholder); }
    public EmptyTag withCondTarget(boolean condition, String target)                { return condAttr(condition, Attr.TARGET, target); }
    public EmptyTag withCondType(boolean condition, String type)                    { return condAttr(condition, Attr.TYPE, type); }
    public EmptyTag withCondRel(boolean condition, String rel)                      { return condAttr(condition, Attr.REL, rel); }
    public EmptyTag withCondSrc(boolean condition, String src)                      { return condAttr(condition, Attr.SRC, src); }
    public EmptyTag withCondValue(boolean condition, String value)                  { return condAttr(condition, Attr.VALUE, value); }

}
