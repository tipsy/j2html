package j2html.tags.specialized;

import j2html.tags.ContainerTag;

import j2html.tags.attributes.*;

public final class DelTag extends ContainerTag<DelTag>
    implements ICite, IDatetime {
    public DelTag() {
        super("del");
    }
}
