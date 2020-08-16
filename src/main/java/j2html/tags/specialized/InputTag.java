package j2html.tags.specialized;

import j2html.tags.EmptyTag;
import j2html.tags.attributes.*;

public final class InputTag extends EmptyTag
    implements
    IAccept<InputTag>,
    IForm<InputTag>,
    IDisabled<InputTag>,
    IFormAction<InputTag>,
    IType<InputTag>,
    IValue<InputTag>
{

    /*
    Attributes implemented are those not found in EmptyTag Base Class and present in
    https://www.w3schools.com/tags/tag_input.asp

    When using InputTag, the attributes specific to InputTag (present in this class) must be set first,
    otherwise the type is watered down to EmptyTag.
     */

    public InputTag() {
        super("input");
    }

    public InputTag withChecked(boolean checked){
        if(checked){
            attr("checked");
        }
        return this;
    }
}
