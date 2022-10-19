package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IRows<T extends Tag<T>> extends IInstance<T> {
    default T withRows(final String rows_) {
        return self().attr("rows", rows_);
    }

    default T withCondRows(final boolean enable, final String rows_) {
        if (enable) {
            self().attr("rows", rows_);
        }
        return self();
    }
}
