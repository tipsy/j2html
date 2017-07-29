package j2html.attributes;


import j2html.printer.ConfigurablePrinter;
import j2html.printer.HtmlPrinter;
import j2html.utils.SimpleEscaper;

public class Attribute {
    private String name;
    private String value;

    public Attribute(String name, String value) {
        this.name = name;
        this.value = SimpleEscaper.escape(value);
    }

    public Attribute(String name) {
        this.name = name;
        this.value = null;
    }

    public String render(HtmlPrinter htmlPrinter) {
        if (name == null) {
            return "";
        }
        if (value == null) {
            return " " + name;
        }
        return (" " + name + "=\"" + value + "\"");
    }

    @Override
    public String toString() {
        return this.render(new ConfigurablePrinter().addLevelIndent());
    }

    public String getName() {
        return name;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
