package j2html.tags.specialized;

import j2html.tags.ContainerTag;

import j2html.tags.attributes.*;

public final class ObjectTag extends ContainerTag<ObjectTag>
    implements IData, IForm, IHeight, IName, IOnabort, IOncanplay, IOnerror, IType, IUsemap, IWidth {
    public ObjectTag() {
        super("object");
    }
}
