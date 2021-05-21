package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface ICols<T extends Tag<T>> extends IInstance<T> {
    default T withCols(final String cols_) {
        return self().attr("cols", cols_);
    }

    default T withCondCols(final boolean enable, final String cols_) {
        if (enable) {
            self().attr("cols", cols_);
        }
        return self();
    }
}
