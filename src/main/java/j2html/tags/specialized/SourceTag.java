package j2html.tags.specialized;

import j2html.tags.EmptyTag;
import j2html.tags.attributes.*;

public final class SourceTag extends EmptyTag<SourceTag>
    implements IMedia, ISizes, ISrc, ISrcset, IType {
    public SourceTag() {
        super("source");
    }
}
