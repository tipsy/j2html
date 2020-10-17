package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IOnsuspend<T extends Tag> extends IInstance<T> {
    default T withOnsuspend(final String onsuspend_) {
        get().attr("onsuspend", onsuspend_);
        return get();
    }

    default T withCondOnsuspend(final boolean enable, final String onsuspend_) {
        if (enable) {
            get().attr("onsuspend", onsuspend_);
        }
        return get();
    }
}
