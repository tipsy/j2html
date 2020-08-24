package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IOnseeking<T extends Tag> extends IInstance<T> {
    default T withOnseeking(final String onseeking_) {
        get().attr("onseeking", onseeking_);
        return get();
    }

    default T withCondOnseeking(final boolean enable, final String onseeking_) {
        if (enable) {
            get().attr("onseeking", onseeking_);
        }
        return get();
    }
}
