package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IOnseeked<T extends Tag<T>> extends IInstance<T> {
    default T withOnseeked(final String onseeked_) {
        return self().attr("onseeked", onseeked_);
    }

    default T withCondOnseeked(final boolean enable, final String onseeked_) {
        if (enable) {
            self().attr("onseeked", onseeked_);
        }
        return self();
    }
}
