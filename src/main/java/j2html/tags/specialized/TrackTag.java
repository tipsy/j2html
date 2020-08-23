package j2html.tags.specialized;

import j2html.tags.EmptyTag;
import j2html.tags.attributes.*;

public final class TrackTag extends EmptyTag<TrackTag>
    implements IDefault<TrackTag>, IKind<TrackTag>, ILabel<TrackTag>, ISrc<TrackTag>, ISrclang<TrackTag> {
    public TrackTag() {
        super("track");
    }
}
