package j2html.tags.specialized;

import j2html.tags.EmptyTag;
import j2html.tags.attributes.*;

public final class AreaTag extends EmptyTag<AreaTag>
    implements IAlt, ICoords, IHref, IHreflang, IMedia, IRel, IShape, ITarget {
    public AreaTag() {
        super("area");
    }
}
