package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IReversed<T extends Tag> extends IInstance<T> {
    default T isReversed() {
        get().attr("reversed");
        return get();
    }

    default T withCondReversed(final boolean enable) {
        if (enable) {
            get().attr("reversed");
        }
        return get();
    }
}
