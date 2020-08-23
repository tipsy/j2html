package j2html.tags.specialized;

import j2html.tags.ContainerTag;

import j2html.tags.attributes.*;

public final class CanvasTag extends ContainerTag<CanvasTag>
    implements IHeight, IWidth {
    public CanvasTag() {
        super("canvas");
    }
}
