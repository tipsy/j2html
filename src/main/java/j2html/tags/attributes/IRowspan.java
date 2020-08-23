package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IRowspan<T extends Tag> extends IInstance<T> {
    default T withRowspan(final String rowspan_) {
        get().attr("rowspan", rowspan_);
        return get();
    }
}
