package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface ICols<T extends Tag> extends IInstance<T> {
    default T withCols(final String cols_) {
        get().attr("cols", cols_);
        return get();
    }

    default T withCondCols(final boolean enable, final String cols_) {
        if (enable) {
            get().attr("cols", cols_);
        }
        return get();
    }
}
