package j2html.tags.specialized;

import j2html.tags.ContainerTag;

import j2html.tags.attributes.*;

public final class TextareaTag extends ContainerTag<TextareaTag>
    implements IAutofocus, ICols, IDirname, IForm, IMaxlength, IName, IPlaceholder, IReadonly, IRequired, IRows, IWrap {
    public TextareaTag() {
        super("textarea");
    }
}
