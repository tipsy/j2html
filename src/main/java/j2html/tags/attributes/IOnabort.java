package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IOnabort<T extends Tag> extends IInstance<T> {
    default T withOnabort(final String onabort_) {
        get().attr("onabort", onabort_);
        return get();
    }

    default T withCondOnabort(final boolean enable, final String onabort_) {
        if (enable) {
            get().attr("onabort", onabort_);
        }
        return get();
    }
}
