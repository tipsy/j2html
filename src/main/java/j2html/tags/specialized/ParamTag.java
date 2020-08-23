package j2html.tags.specialized;

import j2html.tags.EmptyTag;
import j2html.tags.attributes.*;

public final class ParamTag extends EmptyTag<ParamTag>
    implements IName<ParamTag>, IValue<ParamTag> {
    public ParamTag() {
        super("param");
    }
}
