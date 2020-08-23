package j2html.tags.specialized;

import j2html.tags.ContainerTag;
import j2html.tags.attributes.*;

public final class OptionTag extends ContainerTag<OptionTag>
    implements ILabel<OptionTag>, ISelected<OptionTag>, IValue<OptionTag> {
    public OptionTag() {
        super("option");
    }
}
