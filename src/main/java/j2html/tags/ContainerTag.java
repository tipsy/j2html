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
     *
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
     */
    public ContainerTag condWith(boolean condition, DomContent child) {
        return condition ? this.with(child) : this;
    }

    /**
     * Appends a list of DomContent-objects to the end of this element
     *
     * @param children DomContent-objects to be appended
     * @return itself for easy chaining
     */
    public ContainerTag with(List<DomContent> children) {
        if (children != null) {
            for (DomContent child : children) {
                this.with(child);
            }
        }
        return this;
    }

    /**
     * Call with-method based on condition
     * {@link #with(List children)}
     */
    public ContainerTag condWith(boolean condition, List<DomContent> children) {
        return condition ? this.with(children) : this;
    }

    /**
     * Appends the DomContent-objects to the end of this element
     *
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
     */
    public ContainerTag condWith(boolean condition, DomContent... children) {
        return condition ? this.with(children) : this;
    }

    /**
     * Appends a Text-object to this element
     *
     * @param text the text to be appended
     * @return itself for easy chaining
     */
    public ContainerTag withText(String text) {
        return with(new Text(text));
    }

    /**
     * Render the ContainerTag and its children
     */
    @Override
    public String render() {
        if (isDisabled()) {
            return "";
        }

        StringBuilder rendered = new StringBuilder(renderOpenTag());
        if (children != null && children.size() > 0) {
            for (DomContent child : children) {
                rendered.append(child.render());
            }
        }
        rendered.append(renderCloseTag());
        return rendered.toString();
    }

}
