package j2html.tags.specialized;

import j2html.tags.ContainerTag;
import j2html.tags.attributes.IValue;

public final class DataTag extends ContainerTag<DataTag>
    implements IValue<DataTag> {
    public DataTag() {
        super("data");
    }
}
