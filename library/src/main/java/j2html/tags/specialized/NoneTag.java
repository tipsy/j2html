package j2html.tags.specialized;

import j2html.tags.ContainerTag;
import j2html.tags.attributes.*;

public final class NoneTag extends ContainerTag<NoneTag> 
implements IDisplaystyle<NoneTag>,IMathbackground<NoneTag>,IMathcolor<NoneTag>,IMathsize<NoneTag>,IMathvariant<NoneTag>,INonce<NoneTag>,IScriptlevel<NoneTag> {
public NoneTag() {super("none");}
}
