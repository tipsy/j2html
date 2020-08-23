package j2html.tags.specialized;

import j2html.tags.ContainerTag;
import j2html.tags.attributes.IDisabled;
import j2html.tags.attributes.IType;

public final class SelectTag extends ContainerTag
implements
    IDisabled<SelectTag>,
    IType<SelectTag>
{
    public SelectTag() {
        super("select");
    }
}
