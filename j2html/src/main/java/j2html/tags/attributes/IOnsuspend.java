package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IOnsuspend<T extends Tag<T>> extends IInstance<T> {
    default T withOnsuspend(final String onsuspend_) {
        return self().attr("onsuspend", onsuspend_);
    }

    default T withCondOnsuspend(final boolean enable, final String onsuspend_) {
        if (enable) {
            self().attr("onsuspend", onsuspend_);
        }
        return self();
    }
}
