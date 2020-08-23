package j2html.tags.specialized;

import j2html.tags.ContainerTag;
import j2html.tags.attributes.IDisabled;
import j2html.tags.attributes.IForm;
import j2html.tags.attributes.IName;

public final class FieldsetTag extends ContainerTag<FieldsetTag>
    implements IDisabled<FieldsetTag>, IForm<FieldsetTag>, IName<FieldsetTag> {
    public FieldsetTag() {
        super("fieldset");
    }
}
