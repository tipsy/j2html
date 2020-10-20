package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IOnstorage<T extends Tag> extends IInstance<T> {
    default T withOnstorage(final String onstorage_) {
        get().attr("onstorage", onstorage_);
        return get();
    }

    default T withCondOnstorage(final boolean enable, final String onstorage_) {
        if (enable) {
            get().attr("onstorage", onstorage_);
        }
        return get();
    }
}
