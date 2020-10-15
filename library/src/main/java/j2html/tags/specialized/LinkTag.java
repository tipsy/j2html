package j2html.tags.specialized;

import j2html.tags.EmptyTag;
import j2html.tags.attributes.*;

public final class LinkTag extends EmptyTag<LinkTag>
    implements IHref<LinkTag>, IHreflang<LinkTag>, IMedia<LinkTag>, IOnload<LinkTag>, IRel<LinkTag>, ISizes<LinkTag>, IType<LinkTag> {
    public LinkTag() {
        super("link");
    }
}
