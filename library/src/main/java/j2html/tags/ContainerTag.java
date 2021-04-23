package j2html.tags;

import j2html.Config;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ContainerTag<T extends ContainerTag<T>> extends Tag<T> {
//public class ContainerTag extends Tag<ContainerTag> {

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

    private static void indent(Appendable appendable, int level) throws IOException {
        appendable.append(Config.indenter.indent(level, ""));
    }

    private String renderFormatted(int lvl) throws IOException {
        StringBuilder sb = new StringBuilder();
        renderFormatted(lvl, sb);
        return sb.toString();
    }


    private void renderFormatted(int lvl, Appendable sb) throws IOException {

        renderOpenTag(sb, null);
        if (hasTagName() && !isSelfFormattingTag()) {
            sb.append("\n");
        }
        if (!children.isEmpty()) {
            for (DomContent c : children) {
                lvl++;
                if (c instanceof ContainerTag) {
                    if (((ContainerTag) c).hasTagName()) {
                        if (this.isSelfFormattingTag()) {
                            c.render(sb);
                        } else {
                            indent(sb, lvl);
                            ((ContainerTag<?>) c).renderFormatted(lvl, sb);
                        }
                    } else {
                        if (this.isSelfFormattingTag()) {
                            c.render(sb);
                        } else {
                            ((ContainerTag<?>) c).renderFormatted(lvl-1, sb);
                        }
                    }
                } else {
                    if (!this.isSelfFormattingTag()) {
                        indent(sb, lvl);
                    }
                    c.render(sb);
                    if (!this.isSelfFormattingTag()) {
                        sb.append("\n");
                    }
                }
                lvl--;
            }
        }
        if (hasTagName() && !this.isSelfFormattingTag()) {
            indent(sb, lvl);
        }
        renderCloseTag(sb);
        if (hasTagName()) {
            sb.append("\n");
        }
    }

    private boolean isSelfFormattingTag() {
        return "textarea".equals(tagName) || "pre".equals(tagName);
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

    @FunctionalInterface
    private interface ThrowingBiFunction<T, U, R, E extends Exception> {

        R apply(final T t, final U u) throws E;

    }

}
