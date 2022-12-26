package com.j2html.codegen;

import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    private static final Pattern EMPTY_LINE_PATTERN = Pattern.compile("\\s*");
    private static final Pattern COMMENT_PATTERN = Pattern.compile("#.*");
    private static final Pattern NODE_PATTERN = Pattern.compile("(?<type>ELEMENT|EMPTY-ELEMENT|BOOLEAN|ONOFF|STRING)\\[(?<name>\\S+)\\]");
    private static final Pattern ATTRIBUTE_PATTERN = Pattern.compile("ATTRIBUTE\\[(?<element>\\S+):(?<name>\\S+)\\]");

    public interface Listener {
        void lineCommented(int line, String txt);

        void elementDefined(int line, String name);

        void emptyElementDefined(int line, String name);

        void booleanDefined(int line, String name);

        void onOffDefined(int line, String name);

        void stringDefined(int line, String name);

        void attributeDefined(int line, String element, String name);

        void invalidLine(int line, String txt);
    }

    public static void parse(String txt, Listener listener) {
        String[] lines = txt.split("[\r\n]+");

        for (int i = 0; i < lines.length; i++) {
            int number = i + 1;
            String line = lines[i];

            if (match(EMPTY_LINE_PATTERN, line)) continue;

            if (match(COMMENT_PATTERN, line, matcher -> {
                listener.lineCommented(number, line);
            })) continue;

            if (match(NODE_PATTERN, line, matcher -> {
                String type = matcher.group("type");
                String name = matcher.group("name");
                switch (type) {
                    case "ELEMENT":
                        listener.elementDefined(number, name);
                        break;
                    case "EMPTY-ELEMENT":
                        listener.emptyElementDefined(number, name);
                        break;
                    case "BOOLEAN":
                        listener.booleanDefined(number, name);
                        break;
                    case "ONOFF":
                        listener.onOffDefined(number, name);
                        break;
                    case "STRING":
                        listener.stringDefined(number, name);
                        break;
                }
            })) continue;

            if (match(ATTRIBUTE_PATTERN, line, matcher -> {
                listener.attributeDefined(
                    number,
                    matcher.group("element"),
                    matcher.group("name")
                );
            })) continue;

            listener.invalidLine(number, line);
        }
    }

    private static boolean match(Pattern pattern, String txt) {
        return pattern.matcher(txt).matches();
    }

    private static boolean match(Pattern pattern, String txt, Consumer<Matcher> onMatch) {
        Matcher matcher = pattern.matcher(txt);
        if (matcher.matches()) {
            onMatch.accept(matcher);
            return true;
        }
        return false;
    }
}
