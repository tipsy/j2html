package j2html.tags.attributes;

import j2html.tags.Tag;

public interface ISizes<T extends Tag> extends IInstance<T> {
    default T withSizes(final String sizes_) {
        get().attr("sizes", sizes_);
        return get();
    }

    default T withCondSizes(final boolean enable, final String sizes_) {
        if (enable) {
            get().attr("sizes", sizes_);
        }
        return get();
    }
}
