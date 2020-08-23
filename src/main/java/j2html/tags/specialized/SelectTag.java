package j2html.tags.specialized;

import j2html.tags.ContainerTag;
import j2html.tags.attributes.*;

public final class SelectTag extends ContainerTag<SelectTag>
    implements IAutofocus<SelectTag>, IDisabled<SelectTag>, IForm<SelectTag>, IMultiple<SelectTag>, IName<SelectTag>, IRequired<SelectTag>, ISize<SelectTag> {
    public SelectTag() {
        super("select");
    }
}
