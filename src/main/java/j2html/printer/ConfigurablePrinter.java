package j2html.printer;

/**
 * @author chmuchme
 * @since 0.1
 * on 01/05/17.
 */
public class ConfigurablePrinter implements HtmlPrinter{
    private final boolean autoCloseTagIfEmpty;
    private final String indent;
    private int levelIndent = 0;
    public static String TOW_SPACE = "  ";
    public static String FOUR_SPACE = "    ";
    public static String TAB = "    ";

    public ConfigurablePrinter() {
        this.autoCloseTagIfEmpty = false;
        this.indent = "";
    }
    public ConfigurablePrinter(boolean autoCloseTagIfEmpty, String indent) {
        this.autoCloseTagIfEmpty = autoCloseTagIfEmpty;
        this.indent = indent;
    }
    @Override
    public HtmlPrinter addLevelIndent() {
        ConfigurablePrinter printer = new ConfigurablePrinter(autoCloseTagIfEmpty,indent);
        printer.levelIndent = levelIndent+1;
        return printer;
    }

    @Override
    public int getLevelIndent() {
        return levelIndent;
    }

    @Override
    public boolean prettyPrint() {
        return !indent.isEmpty();
    }

    @Override
    public String indent() {
        return indent;
    }

    @Override
    public boolean autoCloseTagIfEmpty() {
        return autoCloseTagIfEmpty;
    }
}
