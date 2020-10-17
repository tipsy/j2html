package j2html.tags.specialized;

import j2html.tags.ContainerTag;
import j2html.tags.attributes.*;

public final class ScriptTag extends ContainerTag<ScriptTag>
    implements IAsync<ScriptTag>, ICharset<ScriptTag>, IDefer<ScriptTag>, IOnerror<ScriptTag>, IOnload<ScriptTag>, ISrc<ScriptTag>, IType<ScriptTag> {
    public ScriptTag() {
        super("script");
    }
}
