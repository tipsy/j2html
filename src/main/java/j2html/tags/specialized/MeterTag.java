package j2html.tags.specialized;

import j2html.tags.ContainerTag;

import j2html.tags.attributes.*;

public final class MeterTag extends ContainerTag<MeterTag>
    implements IForm, IHigh, ILow, IMax, IMin, IOptimum, IValue {
    public MeterTag() {
        super("meter");
    }
}
