package j2html.tags.specialized;

import j2html.tags.ContainerTag;
import j2html.tags.attributes.*;

public final class FormTag extends ContainerTag<FormTag>
    implements IAction<FormTag>, IAutocomplete<FormTag>, IEnctype<FormTag>, IMethod<FormTag>, IName<FormTag>, INovalidate<FormTag>, IOnreset<FormTag>, IOnsubmit<FormTag>, IRel<FormTag>, ITarget<FormTag> {
    public FormTag() {
        super("form");
    }
}
