package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IReversed<T extends Tag> extends IInstance<T> {
    default T withReversed(final String reversed_) {
        get().attr("reversed", reversed_);
        return get();
    }

    default T withCondReversed(final boolean enable, final String reversed_) {
        if (enable) {
            get().attr("reversed", reversed_);
        }
        return get();
    }
}
