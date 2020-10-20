package j2html.tags.specialized.generated;

import j2html.tags.ContainerTag;
import j2html.tags.attributes.IMedia;
import j2html.tags.attributes.IOnerror;
import j2html.tags.attributes.IOnload;
import j2html.tags.attributes.IType;

public final class StyleTag extends ContainerTag<StyleTag>
    implements IMedia<StyleTag>, IOnerror<StyleTag>, IOnload<StyleTag>, IType<StyleTag> {
    public StyleTag() {
        super("style");
    }
}
