package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface ISizes<T extends Tag<T>> extends IInstance<T> {
    default T withSizes(final String sizes_) {
        return self().attr("sizes", sizes_);
    }

    default T withCondSizes(final boolean enable, final String sizes_) {
        if (enable) {
            self().attr("sizes", sizes_);
        }
        return self();
    }
}
