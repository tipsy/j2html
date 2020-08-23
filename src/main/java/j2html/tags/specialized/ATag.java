package j2html.tags.specialized;

import j2html.tags.ContainerTag;

import j2html.tags.attributes.*;

public final class ATag extends ContainerTag<ATag>
    implements IHref, IHreflang, IMedia, IRel, ITarget, IType {
    public ATag() {
        super("a");
    }
}
