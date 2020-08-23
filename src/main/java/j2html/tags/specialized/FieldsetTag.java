package j2html.tags.specialized;

import j2html.tags.ContainerTag;
import j2html.tags.attributes.*;

public final class FieldsetTag extends ContainerTag<FieldsetTag>
    implements IForm, IName {
    public FieldsetTag() {
        super("fieldset");
    }
}
