package j2html.tags.specialized;

import j2html.tags.ContainerTag;
import j2html.tags.attributes.IType;

public final class MenuTag extends ContainerTag<MenuTag>
    implements IType<MenuTag> {
    public MenuTag() {
        super("menu");
    }
}
