package j2html.tags;

import j2html.Config;
import j2html.attributes.Attribute;
import j2html.rendering.DefaultHtmlBuilder;
import j2html.rendering.HtmlBuilder;
import j2html.rendering.TagBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ContainerTag<T extends ContainerTag<T>> extends Tag<T> {

    protected List<DomContent> children;

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
        if (child != null) {
            // in some cases, like when using iff(), we ignore null children
            children.add(child);
        }
        return self();
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
        return condition ? this.with(child) : self();
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
        return self();
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
        return condition ? this.with(children) : self();
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
        return self();
    }


    /**
     * Appends the DomContent-objects in the stream to the end of this element
     *
     * @param children Stream of DomContent-objects to be appended
     * @return itself for easy chaining
     */
    public T with(Stream<DomContent> children) {
        children.forEach(this::with);
        return self();
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
        return condition ? this.with(children) : self();
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

    /**
     * Render the ContainerTag and its children, adding newlines before each
     * child and using Config.indenter to indent child based on how deep
     * in the tree it is
     *
     * @return the rendered and formatted string
     */
    @SuppressWarnings("deprecation")
    public String renderFormatted() {
        try {
            return render(DefaultHtmlBuilder.withConfig(Config.global()).indented(true).inMemory()).toString();
        }catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public <A extends Appendable> A render(HtmlBuilder<A> builder, Object model) throws IOException {
        if (hasTagName()) {
            TagBuilder tagBuilder = builder.appendStartTag(getTagName());
            for(Attribute attribute : getAttributes()){
                attribute.render(tagBuilder, model);
            }
            tagBuilder.completeTag();
        }

        for(DomContent child : children){
            child.render(builder, model);
        }

        if(hasTagName()) {
            builder.appendEndTag(getTagName());
        }

        return builder.output();
    }

    @Override
    @Deprecated
    public void renderModel(Appendable writer, Object model) throws IOException {
        HtmlBuilder<?> builder = (writer instanceof HtmlBuilder)
            ? (HtmlBuilder<?>) writer
            : DefaultHtmlBuilder.withConfig(Config.global()).into(writer);

        render(builder, model);
    }
}
