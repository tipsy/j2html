package j2html.tags.specialized;

import j2html.tags.EmptyTag;
import j2html.tags.attributes.*;

public final class InputTag extends EmptyTag<InputTag>
    implements IAccept, IAlt, IAutocomplete, IAutofocus, IChecked, IDirname, IForm, IFormaction, IHeight, IList, IMax, IMaxlength, IMin, IMultiple, IName, IOnload, IOnsearch, IPattern, IPlaceholder, IReadonly, IRequired, ISize, ISrc, IStep, IType, IValue, IWidth {
    public InputTag() {
        super("input");
    }
}
