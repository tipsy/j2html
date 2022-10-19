package j2html.tags.specialized;

import j2html.tags.ContainerTag;
import j2html.tags.attributes.IName;

public final class SlotTag extends ContainerTag<SlotTag>
    implements IName<SlotTag> {
    public SlotTag() {
        super("slot");
    }
}
