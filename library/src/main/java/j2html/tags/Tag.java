package j2html.tags;

import j2html.attributes.Attr;
import j2html.attributes.Attribute;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

public abstract class Tag<T extends Tag<T>> extends DomContent implements IInstance<T> {
    private final String tagName;
    private final ArrayList<Attribute> attributes;

    protected Tag(String tagName) {
        this.tagName = tagName;
        this.attributes = new ArrayList<>();
    }

    public String getTagName() {
        return this.tagName;
    }

    protected boolean hasTagName() {
        return tagName != null && !tagName.isEmpty();
    }

    protected ArrayList<Attribute> getAttributes() {
        return attributes;
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
                attribute.setValue(value); // update with new value
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
    public T attr(String attribute, Object value) {
        setAttribute(attribute, value == null ? null : String.valueOf(value));
        return self();
    }

    /**
     * Sets a data prefixed attribute
     *
     * @param attribute the attribute name to be prefixed with "data-"
     * @param value     the attribute value
     * @return itself for easy chaining
     */
    public T dataAttr(String attribute, Object value) {
        setAttribute("data-" + attribute, value == null ? null : String.valueOf(value));
        return self();
    }

    /**
     * appends a attribute value to an existing list of value/s
     *
     * @param name  the attribute name
     * @param value the attribute value
     * @return itself for easy chaining
     */
    public T appendAttrValue(String name, String value) {
        if (value == null) {
            return self();
        }
        if (attributes.size() == 0) {
            attributes.add(new Attribute(name, value));
            return self();
        }
        for (Attribute attribute : attributes) {
            if (attribute.getName().equals(name)) {
                String attributeValue = attribute.getValue();
                StringTokenizer st = new StringTokenizer(attributeValue, " ");
                while (st.hasMoreTokens()) {
                    if (value.equals(st.nextToken())) {
                        return self();
                    }
                }
                attribute.setValue(attributeValue + " " + value);
                break;
            }
        }

        return self();
    }

    /**
     * appends a attribute value to an existing list of value/s
     *
     * @param name  the attribute name
     * @param value the attribute value
     * @return itself for easy chaining
     */
    public T removeAttrValue(String name, String value) {
        if (value == null) {
            return self();
        }
        if (attributes.size() == 0) {
            return self();
        }
        for (Attribute attribute : attributes) {
            if (attribute.getName().equals(name)) {
                String attributeValue = attribute.getValue();
                StringTokenizer st = new StringTokenizer(attributeValue, " ");
                StringBuilder newValue = new StringBuilder(attributeValue.length());
                while (st.hasMoreTokens()) {
                    String currentToken = st.nextToken();
                    if (value.equals(currentToken)) {
                        //skip
                    } else {
                        newValue.append(currentToken);
                        if (st.hasMoreTokens())
                            newValue.append(" ");
                    }
                }
                attribute.setValue(newValue.toString());
                break;
            }
        }

        return self();
    }

    /**
     * Adds the specified attribute. If the Tag previously contained an attribute with the same name, the old attribute is replaced by the specified attribute.
     *
     * @param attribute the attribute
     * @return itself for easy chaining
     */
    public T attr(Attribute attribute) {
        Iterator<Attribute> iterator = attributes.iterator();
        String name = attribute.getName();
        if (name != null) {
            // name == null is allowed, but those Attributes are not rendered. So we add them anyway.
            while (iterator.hasNext()) {
                Attribute existingAttribute = iterator.next();
                if (existingAttribute.getName().equals(name)) {
                    iterator.remove();
                }
            }
        }
        attributes.add(attribute);
        return self();
    }

    /**
     * Sets a custom attribute without value
     *
     * @param attribute the attribute name
     * @return itself for easy chaining
     * @see Tag#attr(String, Object)
     */
    public T attr(String attribute) {
        return attr(attribute, null);
    }

    /**
     * Call attr-method based on condition
     * {@link #attr(String attribute, Object value)}
     *
     * @param condition the condition
     * @param attribute the attribute name
     * @param value     the attribute value
     * @return itself for easy chaining
     */
    public T condAttr(boolean condition, String attribute, String value) {
        return (condition ? attr(attribute, value) : self());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Tag)) {
            return false;
        }
        return ((Tag<?>) obj).render().equals(this.render());
    }

    /**
     * Convenience methods that call attr with predefined attributes
     *
     * @return itself for easy chaining
     */
    public T withClasses(String... classes) {
        StringBuilder sb = new StringBuilder();
        for (String s : classes) {
            sb.append(s != null ? s : "").append(" ");
        }
        return attr(Attr.CLASS, sb.toString().trim());
    }

    /*
    Tag.java contains all Global Attributes, Attributes which are
    valid on all HTML Tags. Reference:
    https://www.w3schools.com/tags/ref_standardattributes.asp
    Attributes:

    accesskey
    class
    contenteditable
    data-*
    dir
    draggable
    hidden
    id
    lang
    spellcheck
    style
    tabindex
    title
    translate
     */

    public T withAccesskey(String accesskey){ return attr(Attr.ACCESSKEY, accesskey); }

    public T withClass(String className) { return attr(Attr.CLASS, className); }

    public T isContenteditable(){ return attr(Attr.CONTENTEDITABLE, "true"); }

    public T withData(String dataAttr, String value) { return attr(Attr.DATA + "-" + dataAttr, value); }

    public T withDir(String dir) { return attr(Attr.DIR, dir); }

    public T isDraggable(){ return attr(Attr.DRAGGABLE, "true"); }

    public T isHidden() { return attr(Attr.HIDDEN, null); }

    public T withId(String id) { return attr(Attr.ID, id); }

    public T withLang(String lang) { return attr(Attr.LANG, lang); }

    public T isSpellcheck(){ return attr(Attr.SPELLCHECK, "true"); }

    public T withStyle(String style) { return attr(Attr.STYLE, style); }

    public T withTabindex(int index){ return attr(Attr.TABINDEX, index); }

    public T withTitle(String title) { return attr(Attr.TITLE, title); }

    public T isTranslate(){ return attr(Attr.TRANSLATE, "yes"); }

    // ----- start of withCond$ATTR variants -----
    public T withCondAccessKey(boolean condition, String accesskey){ return condAttr(condition, Attr.ACCESSKEY, accesskey); }

    public T withCondClass(boolean condition, String className) { return condAttr(condition, Attr.CLASS, className); }

    public T withCondContenteditable(boolean condition){ return attr(Attr.CONTENTEDITABLE, (condition)?"true":"false");}

    public T withCondData(boolean condition, String dataAttr, String value) { return condAttr(condition, Attr.DATA + "-" + dataAttr, value); }

    public T withCondDir(boolean condition, String dir) { return condAttr(condition, Attr.DIR, dir); }

    public T withCondDraggable(boolean condition){ return attr(Attr.DRAGGABLE, (condition)?"true":"false"); }

    public T withCondHidden(boolean condition) { return condAttr(condition, Attr.HIDDEN, null); }

    public T withCondId(boolean condition, String id) { return condAttr(condition, Attr.ID, id); }

    public T withCondLang(boolean condition, String lang) { return condAttr(condition, Attr.LANG, lang); }

    public T withCondSpellcheck(boolean condition){ return attr(Attr.SPELLCHECK, (condition)?"true":"false"); }

    public T withCondStyle(boolean condition, String style) { return condAttr(condition, Attr.STYLE, style); }

    public T withCondTabindex(boolean condition, int index){ return condAttr(condition, Attr.TABINDEX, index+""); }

    public T withCondTitle(boolean condition, String title) { return condAttr(condition, Attr.TITLE, title); }

    public T withCondTranslate(boolean condition){ return attr(Attr.TRANSLATE, (condition)?"yes":"no"); }
}
