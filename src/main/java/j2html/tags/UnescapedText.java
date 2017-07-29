package j2html.tags;

import j2html.printer.HtmlPrinter;

public class UnescapedText extends DomContent {

    private String text;

    public UnescapedText(String text) {
        this.text = text;
    }

    @Override
    public String render() {
        return text;
    }

    @Override
    public void render(Appendable writer, HtmlPrinter htmlPrinter) {
        appendCatch(writer, render());
    }
}
