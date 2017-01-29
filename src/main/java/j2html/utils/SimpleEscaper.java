package j2html.utils;

import java.util.HashMap;
import java.util.Map;

public class SimpleEscaper {

    private static Map<Character, String> map = new HashMap<Character, String>() {{
        put('&', "&amp;");
        put('<', "&lt;");
        put('>', "&gt;");
        put('"', "&quot;");
        put('\'', "&#x27;");
    }};

    public static String escape(String s) {
        if(s == null) {
            return null;
        }
        String escapedString = "";
        for(char c : s.toCharArray()) {
            String escaped = map.get(c);
            escapedString += escaped != null ? escaped : c;
        }
        return escapedString;
    }

}
