package j2html.tags.specialized;

import j2html.tags.ContainerTag;
import j2html.tags.attributes.*;

public final class OutputTag extends ContainerTag<OutputTag>
    implements IFor<OutputTag>, IForm<OutputTag>, IName<OutputTag> {
    public OutputTag() {
        super("output");
    }
}
