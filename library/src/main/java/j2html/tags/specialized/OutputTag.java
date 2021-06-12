package j2html.tags.specialized;

import j2html.tags.ContainerTag;
import j2html.tags.attributes.IFor;
import j2html.tags.attributes.IForm;
import j2html.tags.attributes.IName;

public final class OutputTag extends ContainerTag<OutputTag>
    implements IFor<OutputTag>, IForm<OutputTag>, IName<OutputTag> {
    public OutputTag() {
        super("output");
    }
}
