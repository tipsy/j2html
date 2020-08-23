package j2html.tags.specialized;

import j2html.tags.ContainerTag;

import j2html.tags.attributes.*;

public final class OptionTag extends ContainerTag<OptionTag>
    implements ILabel, ISelected, IValue {
    public OptionTag() {
        super("option");
    }
}
