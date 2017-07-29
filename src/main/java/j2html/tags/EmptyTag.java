package j2html.tags;

import j2html.printer.HtmlPrinter;

public class EmptyTag extends Tag<EmptyTag> {

    public EmptyTag(String tagName) {
        super(tagName);
    }

    @Override
    protected boolean isEmpty() {
        return true;
    }

    @Override
    public void render(Appendable writer, HtmlPrinter htmlPrinter) {
        renderOpenTag(writer, htmlPrinter);
        if (htmlPrinter.autoCloseTagIfEmpty()) {
            renderCloseTag(writer, htmlPrinter);
        }
    }

    /**
     * Render the ContainerTag and its children
     *
     * @return the rendered string
     */
    @Override
    public String render(HtmlPrinter htmlPrinter) {
        StringBuilder rendered = new StringBuilder();
        render(rendered, htmlPrinter);
        return rendered.toString();
    }


}
