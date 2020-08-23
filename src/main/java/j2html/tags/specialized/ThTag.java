package j2html.tags.specialized;

import j2html.tags.ContainerTag;
import j2html.tags.attributes.*;

public final class ThTag extends ContainerTag<ThTag>
    implements IColspan<ThTag>, IHeaders<ThTag>, IRowspan<ThTag>, IScope<ThTag> {
    public ThTag() {
        super("th");
    }
}
