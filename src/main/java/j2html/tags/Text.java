package j2html.tags;

import j2html.printer.HtmlPrinter;
import j2html.utils.SimpleEscaper;

public class Text extends DomContent {

    private String text;

    public Text(String text) {
        this.text = text;
    }

    @Override
    public String render() {
        return SimpleEscaper.escape(text);
    }

    @Override
    public String render(HtmlPrinter htmlPrinter) {
        return render();
    }

    @Override
    public void render(Appendable writer, HtmlPrinter htmlPrinter) {
        appendCatch(writer, indent(htmlPrinter));
        appendCatch(writer, render());
    }
}
