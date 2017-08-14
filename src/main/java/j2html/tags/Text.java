package j2html.tags;

import j2html.Config;

public class Text extends DomContent {

    private String text;

    public Text(String text) {
        this.text = text;
    }

    @Override
    public String render() {
        return Config.textEscaper.escape(text);
    }

}
