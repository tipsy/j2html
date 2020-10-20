package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IOncanplay<T extends Tag> extends IInstance<T> {
    default T withOncanplay(final String oncanplay_) {
        get().attr("oncanplay", oncanplay_);
        return get();
    }

    default T withCondOncanplay(final boolean enable, final String oncanplay_) {
        if (enable) {
            get().attr("oncanplay", oncanplay_);
        }
        return get();
    }
}
