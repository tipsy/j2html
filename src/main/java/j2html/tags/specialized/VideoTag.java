package j2html.tags.specialized;

import j2html.tags.ContainerTag;
import j2html.tags.attributes.*;

public final class VideoTag extends ContainerTag<VideoTag>
    implements IAutoplay<VideoTag>, IControls<VideoTag>, IHeight<VideoTag>, ILoop<VideoTag>, IMuted<VideoTag>, IOnabort<VideoTag>, IOncanplay<VideoTag>, IOncanplaythrough<VideoTag>, IOndurationchange<VideoTag>, IOnemptied<VideoTag>, IOnended<VideoTag>, IOnerror<VideoTag>, IOnloadeddata<VideoTag>, IOnloadedmetadata<VideoTag>, IOnloadstart<VideoTag>, IOnpause<VideoTag>, IOnplay<VideoTag>, IOnplaying<VideoTag>, IOnprogress<VideoTag>, IOnratechange<VideoTag>, IOnseeked<VideoTag>, IOnseeking<VideoTag>, IOnstalled<VideoTag>, IOnsuspend<VideoTag>, IOntimeupdate<VideoTag>, IOnvolumechanged<VideoTag>, IOnwaiting<VideoTag>, IPoster<VideoTag>, IPreload<VideoTag>, ISrc<VideoTag>, IWidth<VideoTag> {
    public VideoTag() {
        super("video");
    }
}
