package j2html.tags.specialized;

import j2html.tags.ContainerTag;
import j2html.tags.attributes.*;

public final class MstyleTag extends ContainerTag<MstyleTag> 
implements IDisplaystyle<MstyleTag>,IMathbackground<MstyleTag>,IMathcolor<MstyleTag>,IMathsize<MstyleTag>,IMathvariant<MstyleTag>,INonce<MstyleTag>,IScriptlevel<MstyleTag> {
public MstyleTag() {super("mstyle");}
}
