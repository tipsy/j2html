package j2html.tags.specialized;

import j2html.tags.ContainerTag;

import j2html.tags.attributes.*;

public final class AudioTag extends ContainerTag<AudioTag>
    implements IAutoplay, IControls, ILoop, IMuted, IOnabort, IOncanplay, IOncanplaythrough, IOndurationchange, IOnemptied, IOnended, IOnerror, IOnloadeddata, IOnloadedmetadata, IOnloadstart, IOnpause, IOnplay, IOnplaying, IOnprogress, IOnratechange, IOnseeked, IOnseeking, IOnstalled, IOnsuspend, IOntimeupdate, IOnvolumechanged, IOnwaiting, IPreload, ISrc {
    public AudioTag() {
        super("audio");
    }
}
