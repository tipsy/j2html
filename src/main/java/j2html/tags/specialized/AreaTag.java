package j2html.tags.specialized;

import j2html.tags.EmptyTag;
import j2html.tags.attributes.*;

public final class AreaTag extends EmptyTag<AreaTag>
    implements IAlt<AreaTag>, ICoords<AreaTag>, IHref<AreaTag>, IHreflang<AreaTag>, IMedia<AreaTag>, IRel<AreaTag>, IShape<AreaTag>, ITarget<AreaTag> {
    public AreaTag() {
        super("area");
    }
}
