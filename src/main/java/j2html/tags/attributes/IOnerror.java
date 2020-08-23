package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IOnerror<T extends Tag> extends IInstance<T> {
    default T withOnerror(final String onerror_) {
        get().attr("onerror", onerror_);
        return get();
    }
}
