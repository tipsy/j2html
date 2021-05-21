package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface ILow<T extends Tag<T>> extends IInstance<T> {
    default T withLow(final String low_) {
        return self().attr("low", low_);
    }

    default T withCondLow(final boolean enable, final String low_) {
        if (enable) {
            self().attr("low", low_);
        }
        return self();
    }
}
