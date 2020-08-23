package j2html.tags.specialized;

import j2html.tags.EmptyTag;
import j2html.tags.attributes.IHref;
import j2html.tags.attributes.ITarget;

public final class BaseTag extends EmptyTag<BaseTag>
    implements IHref<BaseTag>, ITarget<BaseTag> {
    public BaseTag() {
        super("base");
    }
}
