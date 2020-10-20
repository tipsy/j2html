package j2html.tags.specialized.generated;

import j2html.tags.ContainerTag;
import j2html.tags.attributes.IDisabled;
import j2html.tags.attributes.ILabel;
import j2html.tags.attributes.ISelected;
import j2html.tags.attributes.IValue;

public final class OptionTag extends ContainerTag<OptionTag>
    implements IDisabled<OptionTag>, ILabel<OptionTag>, ISelected<OptionTag>, IValue<OptionTag> {
    public OptionTag() {
        super("option");
    }
}
