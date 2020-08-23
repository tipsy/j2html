package j2html.tags.specialized;

import j2html.tags.ContainerTag;
import j2html.tags.attributes.IDownload;
import j2html.tags.attributes.IType;

public final class ATag extends ContainerTag
implements
    IType<ATag>,
    IDownload<ATag>
{
    public ATag() {
        super("a");
    }
}
