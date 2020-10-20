package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IRows<T extends Tag> extends IInstance<T> {
    default T withRows(final String rows_) {
        get().attr("rows", rows_);
        return get();
    }

    default T withCondRows(final boolean enable, final String rows_) {
        if (enable) {
            get().attr("rows", rows_);
        }
        return get();
    }
}
