package j2html.tags.specialized;

import j2html.tags.EmptyTag;
import j2html.tags.attributes.ISpan;

public final class ColTag extends EmptyTag<ColTag>
    implements ISpan<ColTag> {
    public ColTag() {
        super("col");
    }
}
