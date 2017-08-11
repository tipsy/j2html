package j2html.utils;

import java.util.ServiceLoader;

public class TextEscaperFactory {

    private static final TextEscaper INSTANCE;

    static {
        TextEscaper current = new SimpleEscaper();

        for (TextEscaper escaper : ServiceLoader.load(TextEscaper.class)) {
            if (escaper.getPriority() > current.getPriority()) {
                current = escaper;
            }
        }

        INSTANCE = current;
    }

    public static TextEscaper getInstance() {
        return INSTANCE;
    }

}
