package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IOnerror<T extends Tag<T>> extends IInstance<T> {
    default T withOnerror(final String onerror_) {
        return self().attr("onerror", onerror_);
    }

    default T withCondOnerror(final boolean enable, final String onerror_) {
        if (enable) {
            self().attr("onerror", onerror_);
        }
        return self();
    }
}
