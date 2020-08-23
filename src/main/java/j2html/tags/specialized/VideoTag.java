package j2html.tags.specialized;

import j2html.tags.ContainerTag;
import j2html.tags.attributes.*;

public final class VideoTag extends ContainerTag<VideoTag>
    implements IAutoplay, IControls, IHeight, ILoop, IMuted, IOnabort, IOncanplay, IOncanplaythrough, IOndurationchange, IOnemptied, IOnended, IOnerror, IOnloadeddata, IOnloadedmetadata, IOnloadstart, IOnpause, IOnplay, IOnplaying, IOnprogress, IOnratechange, IOnseeked, IOnseeking, IOnstalled, IOnsuspend, IOntimeupdate, IOnvolumechanged, IOnwaiting, IPoster, IPreload, ISrc, IWidth {
    public VideoTag() {
        super("video");
    }
}
