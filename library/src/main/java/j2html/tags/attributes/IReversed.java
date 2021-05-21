package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IReversed<T extends Tag<T>> extends IInstance<T> {
    default T isReversed() {
        self().attr("reversed");
        return self();
    }

    default T withCondReversed(final boolean enable) {
        if (enable) {
            self().attr("reversed");
        }
        return self();
    }
}
