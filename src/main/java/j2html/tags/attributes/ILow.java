package j2html.tags.attributes;

import j2html.tags.Tag;

public interface ILow<T extends Tag> extends IInstance<T> {
    default T withLow(final String low_) {
        get().attr("low", low_);
        return get();
    }
}
