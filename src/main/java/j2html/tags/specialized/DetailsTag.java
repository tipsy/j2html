package j2html.tags.specialized;

import j2html.tags.ContainerTag;

import j2html.tags.attributes.*;

public final class DetailsTag extends ContainerTag<DetailsTag>
    implements IOntoggle, IOpen {
    public DetailsTag() {
        super("details");
    }
}
