package j2html.tags.specialized;

import j2html.tags.EmptyTag;
import j2html.tags.attributes.*;

public final class MetaTag extends EmptyTag<MetaTag>
    implements ICharset<MetaTag>, IContent<MetaTag>, IName<MetaTag> {
    public MetaTag() {
        super("meta");
    }
}
