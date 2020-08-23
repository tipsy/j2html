package j2html.tags.specialized;

import j2html.tags.ContainerTag;

import j2html.tags.attributes.*;

public final class OlTag extends ContainerTag<OlTag>
    implements IReversed, IStart {
    public OlTag() {
        super("ol");
    }
}
