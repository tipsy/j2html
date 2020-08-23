package j2html.tags.specialized;

import j2html.tags.ContainerTag;

import j2html.tags.attributes.*;

public final class ThTag extends ContainerTag<ThTag>
    implements IColspan, IHeaders, IRowspan, IScope {
    public ThTag() {
        super("th");
    }
}
