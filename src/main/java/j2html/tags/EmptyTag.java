package j2html.tags;

public class EmptyTag extends Tag<EmptyTag> {

    public EmptyTag(String tagName) {
        super(tagName);
    }

    @Override
    public String render() {
        if (isDisabled()) {
            return "";
        }

        return renderOpenTag();
    }

}
