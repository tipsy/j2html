package j2html.tags;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import j2html.Config;

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
            throw new RuntimeException("Cannot append a tag to itself.");
        }
        if (child == null) {
            return this; // in some cases, like when using iff(), we ignore null children
        }
        children.add(child);
        return this;
    }


    /**
     * Call with-method based on condition
     * {@link #with(DomContent child)}
     *
     * @param condition the condition to use
     * @param child     DomContent-object to be appended if condition met
     * @return itself for easy chaining
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
     *
     * @param condition the condition to use
     * @param children  DomContent-objects to be appended if condition met
     * @return itself for easy chaining
     */
    public ContainerTag condWith(boolean condition, Iterable<? extends DomContent> children) {
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
     *
     * @param condition the condition to use
     * @param children  DomContent-objects to be appended if condition met
     * @return itself for easy chaining
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
    public String renderFormatted() {
        try {
            return renderFormatted(0);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private String renderFormatted(int lvl) throws IOException {
        StringBuilder sb = new StringBuilder();
        renderOpenTag(sb);
        sb.append("\n");
        if (!children.isEmpty()) {
            for (DomContent c : children) {
                lvl++;
                if (c instanceof ContainerTag) {
                    sb.append(Config.indenter.indent(lvl, ((ContainerTag) c).renderFormatted(lvl)));
                } else {
                    sb.append(Config.indenter.indent(lvl, c.render())).append("\n");
                }
                lvl--;
            }
        }
        sb.append(Config.indenter.indent(lvl, ""));
        renderCloseTag(sb);
        sb.append("\n");
        return sb.toString();
    }

    @Override
    public void render(Appendable writer) throws IOException {
        renderOpenTag(writer);
        if (children != null && !children.isEmpty()) {
            for (DomContent child : children) {
                child.render(writer);
            }
        }
        renderCloseTag(writer);
    }

}
