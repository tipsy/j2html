package j2html.tags.specialized;

import j2html.tags.ContainerTag;

import j2html.tags.attributes.*;

public final class ScriptTag extends ContainerTag<ScriptTag>
    implements IAsync, ICharset, IDefer, IOnerror, IOnload, ISrc, IType {
    public ScriptTag() {
        super("script");
    }
}
