package j2html.tags.specialized;

import j2html.tags.EmptyTag;
import j2html.tags.attributes.*;

public final class InputTag extends EmptyTag<InputTag>
    implements IAccept<InputTag>, IAlt<InputTag>, IAutocomplete<InputTag>, IAutofocus<InputTag>, IChecked<InputTag>, IDirname<InputTag>, IForm<InputTag>, IFormaction<InputTag>, IHeight<InputTag>, IList<InputTag>, IMax<InputTag>, IMaxlength<InputTag>, IMin<InputTag>, IMultiple<InputTag>, IName<InputTag>, IOnload<InputTag>, IOnsearch<InputTag>, IPattern<InputTag>, IPlaceholder<InputTag>, IReadonly<InputTag>, IRequired<InputTag>, ISize<InputTag>, ISrc<InputTag>, IStep<InputTag>, IType<InputTag>, IValue<InputTag>, IWidth<InputTag> {
    public InputTag() {
        super("input");
    }
}
