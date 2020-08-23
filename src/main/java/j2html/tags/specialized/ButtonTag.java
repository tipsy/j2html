package j2html.tags.specialized;

import j2html.tags.ContainerTag;
import j2html.tags.attributes.IDisabled;
import j2html.tags.attributes.IForm;
import j2html.tags.attributes.IType;

public final class ButtonTag extends ContainerTag
implements
    IType<ButtonTag>,
    IDisabled<ButtonTag>,
    IForm<ButtonTag>
{
    public ButtonTag() {
        super("button");
    }
}
