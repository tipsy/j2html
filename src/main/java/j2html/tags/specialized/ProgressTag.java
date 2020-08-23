package j2html.tags.specialized;

import j2html.tags.ContainerTag;

import j2html.tags.attributes.*;

public final class ProgressTag extends ContainerTag<ProgressTag>
    implements IMax, IValue {
    public ProgressTag() {
        super("progress");
    }
}
