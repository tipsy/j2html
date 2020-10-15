package j2html.tags.specialized;

import j2html.tags.ContainerTag;
import j2html.tags.attributes.ICite;

public final class BlockquoteTag extends ContainerTag<BlockquoteTag>
    implements ICite<BlockquoteTag> {
    public BlockquoteTag() {
        super("blockquote");
    }
}
