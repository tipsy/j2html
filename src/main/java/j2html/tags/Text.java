package j2html.tags;

import j2html.utils.TextEscaperFactory;

public class Text extends DomContent {

    private String text;

    public Text(String text) {
        this.text = text;
    }

    @Override
    public String render() {
        return TextEscaperFactory.getInstance().escape(text);
    }

}
