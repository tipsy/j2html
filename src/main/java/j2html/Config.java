package j2html;

import java.util.Collections;

import j2html.utils.SimpleEscaper;
import j2html.utils.TextEscaper;
import j2html.utils.Indenter;

public class Config {

    private Config() {
    }

    private static String FOUR_SPACES = "    ";

    public static TextEscaper textEscaper = new SimpleEscaper();

    public static Indenter indenter = (level, text) -> String.join("", Collections.nCopies(level, FOUR_SPACES)) + text;

    public static boolean closeEmptyTags = false;

}
