package j2html.tags.specialized;

import j2html.tags.ContainerTag;
import j2html.tags.attributes.*;

public final class ATag extends ContainerTag<ATag>
    implements IDownload<ATag>, IHref<ATag>, IHreflang<ATag>, IMedia<ATag>, IRel<ATag>, ITarget<ATag>, IType<ATag> {
    public ATag() {
        super("a");
    }
}
