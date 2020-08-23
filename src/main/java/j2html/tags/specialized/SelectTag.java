package j2html.tags.specialized;

import j2html.tags.ContainerTag;

import j2html.tags.attributes.*;

public final class SelectTag extends ContainerTag<SelectTag>
    implements IAutofocus, IForm, IMultiple, IName, IRequired, ISize {
    public SelectTag() {
        super("select");
    }
}
