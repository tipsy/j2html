package j2html.tags.specialized;

import j2html.tags.ContainerTag;
import j2html.tags.attributes.IFor;
import j2html.tags.attributes.IForm;

public final class LabelTag extends ContainerTag<LabelTag>
    implements IFor<LabelTag>, IForm<LabelTag> {
    public LabelTag() {
        super("label");
    }
}
