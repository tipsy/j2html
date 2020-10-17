package j2html.tags.specialized;

import j2html.tags.ContainerTag;
import j2html.tags.attributes.*;

public final class MeterTag extends ContainerTag<MeterTag>
    implements IForm<MeterTag>, IHigh<MeterTag>, ILow<MeterTag>, IMax<MeterTag>, IMin<MeterTag>, IOptimum<MeterTag>, IValue<MeterTag> {
    public MeterTag() {
        super("meter");
    }
}
