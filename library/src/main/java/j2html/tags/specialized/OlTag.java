package j2html.tags.specialized;

import j2html.tags.ContainerTag;
import j2html.tags.attributes.IReversed;
import j2html.tags.attributes.IStart;

public final class OlTag extends ContainerTag<OlTag>
    implements IReversed<OlTag>, IStart<OlTag> {
    public OlTag() {
        super("ol");
    }
}
