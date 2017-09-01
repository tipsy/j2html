package j2html.tags;

import j2html.Config;

public class EmptyTag extends Tag<EmptyTag> {

    public EmptyTag(String tagName) {
        super(tagName);
    }

    @Override
    public String render() {
        if (Config.closeEmptyTags) {
            String tag = renderOpenTag();
            return tag.substring(0, tag.length() - 1) + "/>";
        }
        return renderOpenTag();
    }

}
