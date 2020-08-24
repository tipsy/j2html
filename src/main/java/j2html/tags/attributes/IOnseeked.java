package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IOnseeked<T extends Tag> extends IInstance<T> {
    default T withOnseeked(final String onseeked_) {
        get().attr("onseeked", onseeked_);
        return get();
    }

    default T withCondOnseeked(final boolean enable, final String onseeked_) {
        if (enable) {
            get().attr("onseeked", onseeked_);
        }
        return get();
    }
}
