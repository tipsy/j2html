package j2html.tags.specialized;

import j2html.tags.ContainerTag;
import j2html.tags.attributes.*;

public final class DelTag extends ContainerTag<DelTag>
    implements ICite<DelTag>, IDatetime<DelTag> {
    public DelTag() {
        super("del");
    }
}
