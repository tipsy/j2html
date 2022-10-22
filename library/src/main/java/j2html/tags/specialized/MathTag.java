package j2html.tags.specialized;

import j2html.tags.ContainerTag;
import j2html.tags.attributes.*;

public final class MathTag extends ContainerTag<MathTag> 
implements IDisplaystyle<MathTag>,IMathbackground<MathTag>,IMathcolor<MathTag>,IMathsize<MathTag>,IMathvariant<MathTag>,INonce<MathTag>,IScriptlevel<MathTag> {
public MathTag() {super("math");}
}
