package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IOnplay<T extends Tag<T>> extends IInstance<T> {
    default T withOnplay(final String onplay_) {
        return self().attr("onplay", onplay_);
    }

    default T withCondOnplay(final boolean enable, final String onplay_) {
        if (enable) {
            self().attr("onplay", onplay_);
        }
        return self();
    }
}
