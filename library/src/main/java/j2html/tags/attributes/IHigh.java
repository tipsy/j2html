package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IHigh<T extends Tag> extends IInstance<T> {
    default T withHigh(final String high_) {
        get().attr("high", high_);
        return get();
    }

    default T withCondHigh(final boolean enable, final String high_) {
        if (enable) {
            get().attr("high", high_);
        }
        return get();
    }
}
