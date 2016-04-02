package j2html.tags;

import static org.apache.commons.lang3.StringEscapeUtils.escapeHtml4;

public class Text extends DomContent {

    private String text;

    public Text(String text) {
        this.text = text;
    }

    @Override
    public String render() {
        return escapeHtml4(text);
    }

}
