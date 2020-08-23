package j2html.tags.specialized;

import j2html.tags.EmptyTag;
import j2html.tags.attributes.*;

public final class ImgTag extends EmptyTag<ImgTag>
    implements IAlt, IHeight, IIsmap, IOnabort, IOnerror, IOnload, ISizes, ISrc, ISrcset, IUsemap, IWidth {
    public ImgTag() {
        super("img");
    }
}
