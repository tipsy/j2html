package j2html.tags.specialized;

import j2html.tags.ContainerTag;
import j2html.tags.attributes.*;

public final class LiTag extends ContainerTag<LiTag>
    implements IValue<LiTag> {
    public LiTag() {
        super("li");
    }
}
