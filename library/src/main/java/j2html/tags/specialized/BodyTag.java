package j2html.tags.specialized;

import j2html.tags.ContainerTag;
import j2html.tags.attributes.*;

public final class BodyTag extends ContainerTag<BodyTag>
        implements
            IOnafterprint<BodyTag>,
            IOnbeforeprint<BodyTag>,
            IOnbeforeunload<BodyTag>,
            IOnerror<BodyTag>,
            IOnhashchange<BodyTag>,
            IOnload<BodyTag>,
            IOnoffline<BodyTag>,
            IOnonline<BodyTag>,
            IOnpagehide<BodyTag>,
            IOnpageshow<BodyTag>,
            IOnpopstate<BodyTag>,
            IOnresize<BodyTag>,
            IOnstorage<BodyTag>,
            IOnunload<BodyTag> {

    public BodyTag() {
        super("body");
    }
}
