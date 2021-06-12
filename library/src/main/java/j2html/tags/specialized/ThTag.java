package j2html.tags.specialized;

import j2html.tags.ContainerTag;
import j2html.tags.attributes.IColspan;
import j2html.tags.attributes.IHeaders;
import j2html.tags.attributes.IRowspan;
import j2html.tags.attributes.IScope;

public final class ThTag extends ContainerTag<ThTag>
    implements IColspan<ThTag>, IHeaders<ThTag>, IRowspan<ThTag>, IScope<ThTag> {
    public ThTag() {
        super("th");
    }
}
