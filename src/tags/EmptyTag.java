package j2html.src.tags;

import j2html.src.attributes.Attr;
import j2html.src.attributes.Attribute;

public class EmptyTag extends Tag {

    protected EmptyTag(String tagType) {
        super(tagType);
    }

    public EmptyTag attr(String attribute, String value) {
        setAttribute(attribute, value);
        return this;
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
    public EmptyTag withType(String type)                     { return attr(Attr.TYPE, type); }
    public EmptyTag withRel(String rel)                       { return attr(Attr.REL, rel); }
    public EmptyTag withSrc(String src)                       { return attr(Attr.SRC, src); }
    public EmptyTag withValue(String value)                   { return attr(Attr.VALUE, value); }

}
