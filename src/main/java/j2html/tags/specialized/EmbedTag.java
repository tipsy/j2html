package j2html.tags.specialized;

import j2html.tags.EmptyTag;
import j2html.tags.attributes.*;

public final class EmbedTag extends EmptyTag<EmbedTag>
    implements IHeight<EmbedTag>, IOnabort<EmbedTag>, IOncanplay<EmbedTag>, IOnerror<EmbedTag>, ISrc<EmbedTag>, IType<EmbedTag>, IWidth<EmbedTag> {
    public EmbedTag() {
        super("embed");
    }
}
