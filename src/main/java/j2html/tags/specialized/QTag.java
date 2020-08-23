package j2html.tags.specialized;

import j2html.tags.ContainerTag;
import j2html.tags.attributes.ICite;

public final class QTag extends ContainerTag<QTag>
    implements ICite<QTag> {
    public QTag() {
        super("q");
    }
}
