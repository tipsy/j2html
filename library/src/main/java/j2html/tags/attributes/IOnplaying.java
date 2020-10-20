package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IOnplaying<T extends Tag> extends IInstance<T> {
    default T withOnplaying(final String onplaying_) {
        get().attr("onplaying", onplaying_);
        return get();
    }

    default T withCondOnplaying(final boolean enable, final String onplaying_) {
        if (enable) {
            get().attr("onplaying", onplaying_);
        }
        return get();
    }
}
