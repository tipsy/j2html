package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IMuted<T extends Tag> extends IInstance<T> {
    default T isMuted() {
        get().attr("muted");
        return get();
    }

    default T withCondMuted(final boolean enable) {
        if (enable) {
            get().attr("muted");
        }
        return get();
    }
}
