package j2html.tags;

import java.util.*;

public class ContainerTag extends Tag<ContainerTag> {

    private List<DomContent> children;

    public ContainerTag(String tagName) {
        super(tagName);
        this.children = new ArrayList<>();
    }

    
    
    /**
     * Appends a DomContent-object to the end of this element
     * @param child DomContent-object to be appended
     * @return itself for easy chaining
     */
    public ContainerTag with(DomContent child) {
        if (this == child) {
            throw new Error("Cannot append a tag to itself.");
        }
        children.add(child);
        return this;
    }

    
    
    /**
     * Call with-method based on condition
     * {@link #with(DomContent child)}
     * @param condition the condition to use
     * @param child DomContent-object to be appended if condition met
     * @return itself for easy chaining
     */
    public ContainerTag condWith(boolean condition, DomContent child) {
        return condition ? this.with(child) : this;
    }

    
    /**
     * Appends a list of DomContent-objects to the end of this element
     * @param children DomContent-objects to be appended
     * @return itself for easy chaining
     */
    public ContainerTag with(Iterable<? extends DomContent> children) {
        if (children != null) {
            for (DomContent child : children) {
                this.with(child);
            }
        }
        return this;
    }

    
    
    /**
     * Call with-method based on condition
     * {@link #with(java.lang.Iterable)}
     * @param condition the condition to use
     * @param children DomContent-objects to be appended if condition met
     * @return itself for easy chaining
     */
    public ContainerTag condWith(boolean condition, Iterable<? extends DomContent> children) {
        return condition ? this.with(children) : this;
    }

    
    /**
     * Appends the DomContent-objects to the end of this element
     * @param children DomContent-objects to be appended
     * @return itself for easy chaining
     */
    public ContainerTag with(DomContent... children) {
        for (DomContent child : children) {
            with(child);
        }
        return this;
    }

    
    /**
     * Call with-method based on condition
     * {@link #with(DomContent... children)}
     * @param condition the condition to use
     * @param children DomContent-objects to be appended if condition met
     * @return itself for easy chaining
     */
    public ContainerTag condWith(boolean condition, DomContent... children) {
        return condition ? this.with(children) : this;
    }

    
    /**
     * Appends a Text-object to this element
     * @param text the text to be appended
     * @return itself for easy chaining
     */
    public ContainerTag withText(String text) {
        return with(new Text(text));
    }

    
    /**
     * Render the ContainerTag and its children
     * @return the rendered string
     */
    @Override
    public String render() {
        StringBuilder rendered = new StringBuilder(renderOpenTag());
        if (children != null && !children.isEmpty()) {
            for (DomContent child : children) {
                rendered.append(child.render());
            }
        }
        rendered.append(renderCloseTag());
        return rendered.toString();
    }

}
