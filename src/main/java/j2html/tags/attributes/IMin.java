package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IMin<T extends Tag> extends IInstance<T> {
    default T withMin(final String min_) {
        get().attr("min", min_);
        return get();
    }
}
