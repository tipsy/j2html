package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IPreload<T extends Tag> extends IInstance<T> {
    default T withPreload(final String preload_) {
        get().attr("preload", preload_);
        return get();
    }

    default T withCondPreload(final boolean enable, final String preload_) {
        if (enable) {
            get().attr("preload", preload_);
        }
        return get();
    }
}
