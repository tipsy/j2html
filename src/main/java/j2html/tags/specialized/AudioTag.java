package j2html.tags.specialized;

import j2html.tags.ContainerTag;
import j2html.tags.attributes.*;

public final class AudioTag extends ContainerTag<AudioTag>
    implements IAutoplay<AudioTag>, IControls<AudioTag>, ILoop<AudioTag>, IMuted<AudioTag>, IOnabort<AudioTag>, IOncanplay<AudioTag>, IOncanplaythrough<AudioTag>, IOndurationchange<AudioTag>, IOnemptied<AudioTag>, IOnended<AudioTag>, IOnerror<AudioTag>, IOnloadeddata<AudioTag>, IOnloadedmetadata<AudioTag>, IOnloadstart<AudioTag>, IOnpause<AudioTag>, IOnplay<AudioTag>, IOnplaying<AudioTag>, IOnprogress<AudioTag>, IOnratechange<AudioTag>, IOnseeked<AudioTag>, IOnseeking<AudioTag>, IOnstalled<AudioTag>, IOnsuspend<AudioTag>, IOntimeupdate<AudioTag>, IOnvolumechanged<AudioTag>, IOnwaiting<AudioTag>, IPreload<AudioTag>, ISrc<AudioTag> {
    public AudioTag() {
        super("audio");
    }
}
