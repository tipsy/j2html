package j2html.utils;

public class SimpleHtmlEscaper {

    /**
     * Simple approach from http://stackoverflow.com/a/25228492/4893160 ... good enough ?
     * Apache StringEscapeUtils is extremely slow compared (probably because it's a lot safer!)
     * @param s the string to be escaped
     * @return the escaped string
     */
    public static String escape(String s) {
        if("".equals(s)){ return ""; }
        if(s == null) { return null; }
        StringBuilder out = new StringBuilder(Math.max(16, s.length()));
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c > 127 || c == '"' || c == '<' || c == '>' || c == '&') {
                out.append("&#");
                out.append((int) c);
                out.append(';');
            } else {
                out.append(c);
            }
        }
        return out.toString();
    }

}
