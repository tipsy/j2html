package j2html.tags.specialized;

import j2html.tags.ContainerTag;
import j2html.tags.attributes.*;

public final class TimeTag extends ContainerTag<TimeTag>
    implements IDatetime<TimeTag> {
    public TimeTag() {
        super("time");
    }
}
