package j2html.tags.specialized;

import j2html.tags.ContainerTag;

import j2html.tags.attributes.*;

public final class StyleTag extends ContainerTag<StyleTag>
    implements IMedia, IOnerror, IOnload, IType {
    public StyleTag() {
        super("style");
    }
}
