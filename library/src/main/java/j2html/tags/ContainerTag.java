package j2html.tags;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import j2html.attributes.Attribute;

public class ContainerTag<T extends ContainerTag<T>> extends Tag<T> {

    private final List<DomContent> children;

    public ContainerTag(String tagName) {
        super(tagName);
        this.children = new ArrayList<>();
    }


    /**
     * Appends a DomContent-object to the end of this element
     *
     * @param child DomContent-object to be appended
     * @return itself for easy chaining
     */
    public T with(DomContent child) {
        if (this == child) {
            throw new RuntimeException("Cannot append a tag to itself.");
        }
        if (child == null) {
            return (T)this; // in some cases, like when using iff(), we ignore null children
        }
        children.add(child);
        return (T)this;
    }


    /**
     * Call with-method based on condition
     * {@link #with(DomContent child)}
     *
     * @param condition the condition to use
     * @param child     DomContent-object to be appended if condition met
     * @return itself for easy chaining
     */
    public T condWith(boolean condition, DomContent child) {
        return condition ? this.with(child) : (T)this;
    }


    /**
     * Appends a list of DomContent-objects to the end of this element
     *
     * @param children DomContent-objects to be appended
     * @return itself for easy chaining
     */
    public T with(Iterable<? extends DomContent> children) {
        if (children != null) {
            for (DomContent child : children) {
                this.with(child);
            }
        }
        return (T)this;
    }


    /**
     * Call with-method based on condition
     * {@link #with(java.lang.Iterable)}
     *
     * @param condition the condition to use
     * @param children  DomContent-objects to be appended if condition met
     * @return itself for easy chaining
     */
    public T condWith(boolean condition, Iterable<? extends DomContent> children) {
        return condition ? this.with(children) : (T)this;
    }


    /**
     * Appends the DomContent-objects to the end of this element
     *
     * @param children DomContent-objects to be appended
     * @return itself for easy chaining
     */
    public T with(DomContent... children) {
        for (DomContent child : children) {
            with(child);
        }
        return (T)this;
    }


    /**
     * Appends the DomContent-objects in the stream to the end of this element
     *
     * @param children Stream of DomContent-objects to be appended
     * @return itself for easy chaining
     */
    public T with(Stream<DomContent> children) {
        children.forEach(this::with);
        return (T)this;
    }


    /**
     * Call with-method based on condition
     * {@link #with(DomContent... children)}
     *
     * @param condition the condition to use
     * @param children  DomContent-objects to be appended if condition met
     * @return itself for easy chaining
     */
    public T condWith(boolean condition, DomContent... children) {
        return condition ? this.with(children) : (T)this;
    }


    /**
     * Appends a Text-object to this element
     *
     * @param text the text to be appended
     * @return itself for easy chaining
     */
    public T withText(String text) {
        return with(new Text(text));
    }

    /**
     * Gets number of child nodes this tag element contains
     */
    public int getNumChildren() {
        return children.size();
    }

    @Override
    public void renderFormatted(final Appendable writer) throws IOException {
        renderFormatted(writer, 0);
    }

    protected void renderFormatted(Appendable writer, int level) throws IOException {
        final boolean selfFormattingTag = isSelfFormattingTag();
        if (hasTagName()) {
            indent(writer, level);
            renderOpenTag(writer, null);
            if (!selfFormattingTag) {
                writer.append("\n");
            }
        } else {
            level--;
        }

        for (DomContent c : children) {
            if (selfFormattingTag) {
                c.render(writer);
            } else {
                c.renderFormatted(writer, level + 1);
            }
        }

        if (hasTagName()) {
            if (!selfFormattingTag) {
                indent(writer, level);
            }
            renderCloseTag(writer);
            writer.append("\n");
        }
    }

    private boolean isSelfFormattingTag() {
        return "textarea".equals(tagName) || "pre".equals(tagName);
    }

    protected void renderOpenTag(Appendable writer, Object model) throws IOException {
        if (!hasTagName()) { // avoid <null> and <> tags
            return;
        }
        writer.append("<").append(tagName);
        for (Attribute attribute : getAttributes()) {
            attribute.renderModel(writer, model);
        }
        writer.append(">");
    }

    protected void renderCloseTag(Appendable writer) throws IOException {
        if (!hasTagName()) { // avoid <null> and <> tags
            return;
        }
        writer.append("</");
        writer.append(tagName);
        writer.append(">");
    }

    @Override
    public void renderModel(Appendable writer, Object model) throws IOException {
        renderOpenTag(writer, model);
        if (children != null && !children.isEmpty()) {
            for (DomContent child : children) {
                child.renderModel(writer, model);
            }
        }
        renderCloseTag(writer);
    }
}
