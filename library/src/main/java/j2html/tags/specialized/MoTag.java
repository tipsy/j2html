package j2html.tags.specialized;

import j2html.tags.ContainerTag;
import j2html.tags.attributes.*;

public final class MoTag extends ContainerTag<MoTag> 
implements IDisplaystyle<MoTag>,IMathbackground<MoTag>,IMathcolor<MoTag>,IMathsize<MoTag>,IMathvariant<MoTag>,INonce<MoTag>,IScriptlevel<MoTag> {
public MoTag() {super("mo");}
}
