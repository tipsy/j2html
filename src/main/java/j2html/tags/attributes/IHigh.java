package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IHigh<T extends Tag> extends IInstance<T> {
    default T withHigh(final String high_) {
        get().attr("high", high_);
        return get();
    }
}
