package j2html.tags.specialized;

import j2html.tags.ContainerTag;
import j2html.tags.attributes.*;

public final class TextareaTag extends ContainerTag<TextareaTag>
    implements IAutofocus<TextareaTag>, ICols<TextareaTag>, IDirname<TextareaTag>, IForm<TextareaTag>, IMaxlength<TextareaTag>, IName<TextareaTag>, IPlaceholder<TextareaTag>, IReadonly<TextareaTag>, IRequired<TextareaTag>, IRows<TextareaTag>, IWrap<TextareaTag> {
    public TextareaTag() {
        super("textarea");
    }
}
