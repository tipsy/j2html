package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IOnplaying<T extends Tag> extends IInstance<T> {
    default T withOnplaying(final String onplaying_) {
        get().attr("onplaying", onplaying_);
        return get();
    }
}
