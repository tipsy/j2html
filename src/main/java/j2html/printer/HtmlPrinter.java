package j2html.printer;

/**
 * @author chmuchme
 * @since 0.1
 * on 01/05/17.
 */
public interface HtmlPrinter {
    HtmlPrinter addLevelIndent();

    int getLevelIndent();

    String indent();

    boolean autoCloseTagIfEmpty();

    boolean prettyPrint();
}
