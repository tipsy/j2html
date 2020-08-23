package j2html.tags.specialized;

import j2html.tags.ContainerTag;
import j2html.tags.attributes.*;

public final class ColgroupTag extends ContainerTag<ColgroupTag>
    implements ISpan<ColgroupTag> {
    public ColgroupTag() {
        super("colgroup");
    }
}
