package j2html.tags.specialized;

import j2html.tags.ContainerTag;

import j2html.tags.attributes.*;

public final class ButtonTag extends ContainerTag<ButtonTag>
    implements IAutofocus, IForm, IFormaction, IName, IType, IValue {
    public ButtonTag() {
        super("button");
    }
}
