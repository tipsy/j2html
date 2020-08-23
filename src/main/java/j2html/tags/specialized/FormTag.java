package j2html.tags.specialized;

import j2html.tags.ContainerTag;
import j2html.tags.attributes.*;

public final class FormTag extends ContainerTag<FormTag>
    implements IAction, IAutocomplete, IEnctype, IMethod, IName, INovalidate, IOnreset, IOnsubmit, IRel, ITarget {
    public FormTag() {
        super("form");
    }
}
