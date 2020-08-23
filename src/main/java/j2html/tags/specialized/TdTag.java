package j2html.tags.specialized;

import j2html.tags.ContainerTag;
import j2html.tags.attributes.IColspan;
import j2html.tags.attributes.IHeaders;
import j2html.tags.attributes.IRowspan;

public final class TdTag extends ContainerTag<TdTag>
    implements IColspan<TdTag>, IHeaders<TdTag>, IRowspan<TdTag> {
    public TdTag() {
        super("td");
    }
}
