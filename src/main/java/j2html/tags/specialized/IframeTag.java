package j2html.tags.specialized;

import j2html.tags.ContainerTag;

import j2html.tags.attributes.*;

public final class IframeTag extends ContainerTag<IframeTag>
    implements IHeight, IName, IOnload, ISandbox, ISrc, ISrcdoc, IWidth {
    public IframeTag() {
        super("iframe");
    }
}
