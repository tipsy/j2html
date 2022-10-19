package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IWrap<T extends Tag<T>> extends IInstance<T> {
    default T withWrap(final String wrap_) {
        return self().attr("wrap", wrap_);
    }

    default T withCondWrap(final boolean enable, final String wrap_) {
        if (enable) {
            self().attr("wrap", wrap_);
        }
        return self();
    }
}
