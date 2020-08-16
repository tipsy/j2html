package j2html.tags.specialized;

import j2html.tags.ContainerTag;
import j2html.tags.EmptyTag;
import j2html.tags.attributes.*;

public final class ButtonTag extends ContainerTag
    implements
    IForm<ButtonTag>,
    IDisabled<ButtonTag>,
    IFormAction<ButtonTag>,
    IType<ButtonTag>,
    IValue<ButtonTag>
{

    public ButtonTag() {
        super("button");
    }
}
