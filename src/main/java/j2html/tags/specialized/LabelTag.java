package j2html.tags.specialized;

import j2html.tags.ContainerTag;

import j2html.tags.attributes.*;

public final class LabelTag extends ContainerTag<LabelTag>
    implements IFor, IForm {
    public LabelTag() {
        super("label");
    }
}
