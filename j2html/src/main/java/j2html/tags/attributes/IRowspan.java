package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IRowspan<T extends Tag<T>> extends IInstance<T> {
    default T withRowspan(final String rowspan_) {
        return self().attr("rowspan", rowspan_);
    }

    default T withCondRowspan(final boolean enable, final String rowspan_) {
        if (enable) {
            self().attr("rowspan", rowspan_);
        }
        return self();
    }
}
