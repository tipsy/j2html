package j2html.utils;

import java.util.Collections;

@FunctionalInterface
public interface Indenter {

    String indent(int level, String text);

    static Indenter with(String indentString) {
        return (level, text) -> String.join("", Collections.nCopies(level, indentString)) + text;
    }

    static Indenter defaults() {
        return with("    ");
    }
}
