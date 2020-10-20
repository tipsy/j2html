package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IOnplay<T extends Tag> extends IInstance<T> {
    default T withOnplay(final String onplay_) {
        get().attr("onplay", onplay_);
        return get();
    }

    default T withCondOnplay(final boolean enable, final String onplay_) {
        if (enable) {
            get().attr("onplay", onplay_);
        }
        return get();
    }
}
