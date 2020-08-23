package j2html.tags.specialized;

import j2html.tags.ContainerTag;

import j2html.tags.attributes.*;

public final class TdTag extends ContainerTag<TdTag>
    implements IColspan, IHeaders, IRowspan {
    public TdTag() {
        super("td");
    }
}
