package j2html.tags.specialized;

import j2html.tags.ContainerTag;
import j2html.tags.attributes.*;

public final class SelectTag extends ContainerTag
    implements
    IDisabled<SelectTag>,
    IForm<SelectTag>
{

    public SelectTag() {
        super("select");
    }
}
