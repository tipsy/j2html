package j2html.src.htmlEscaper;

public class HtmlEscaper {

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
