package j2html;

import j2html.utils.SimpleEscaper;
import j2html.utils.TextEscaper;

public class Config {

    private Config() {
    }

    public static TextEscaper textEscaper = new SimpleEscaper();
    public static boolean closeEmptyTags = false;

}
