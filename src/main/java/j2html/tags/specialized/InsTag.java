package j2html.tags.specialized;

import j2html.tags.ContainerTag;

import j2html.tags.attributes.*;

public final class InsTag extends ContainerTag<InsTag>
    implements ICite, IDatetime {
    public InsTag() {
        super("ins");
    }
}
