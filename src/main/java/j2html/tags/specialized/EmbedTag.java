package j2html.tags.specialized;

import j2html.tags.EmptyTag;
import j2html.tags.attributes.*;

public final class EmbedTag extends EmptyTag<EmbedTag>
    implements IHeight, IOnabort, IOncanplay, IOnerror, ISrc, IType, IWidth {
    public EmbedTag() {
        super("embed");
    }
}
