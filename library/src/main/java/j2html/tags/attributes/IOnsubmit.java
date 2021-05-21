package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IOnsubmit<T extends Tag<T>> extends IInstance<T> {
    default T withOnsubmit(final String onsubmit_) {
        return self().attr("onsubmit", onsubmit_);
    }

    default T withCondOnsubmit(final boolean enable, final String onsubmit_) {
        if (enable) {
            self().attr("onsubmit", onsubmit_);
        }
        return self();
    }
}
