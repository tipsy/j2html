package j2html.tags;

import j2html.printer.ConfigurablePrinter;
import j2html.printer.HtmlPrinter;
import j2html.RenderException;

import java.io.IOException;

public abstract class DomContent {

    public String render(){
        return render(new ConfigurablePrinter());
    }
    public String render(HtmlPrinter htmlPrinter){
        StringBuilder b = new StringBuilder();
        render(b, htmlPrinter);
        return b.toString();
    }

    public void render(Appendable writer) throws IOException {
        render(writer, new ConfigurablePrinter());
    }

    public abstract void render(Appendable writer, HtmlPrinter htmlPrinter);

    @Override
    public String toString() {
        return render(new ConfigurablePrinter());
    }

    protected final void appendCatch(Appendable appendable, String s){
        try {
            appendable.append(s);
        } catch (IOException e) {
            throw new RenderException(e);
        }
    }

    protected final String indent(HtmlPrinter htmlPrinter){
        if (htmlPrinter.getLevelIndent() == 0){
            return "";
        }
        return String.format("%0" + htmlPrinter.getLevelIndent() + "d", 0).replace("0",htmlPrinter.indent());
    }

}
