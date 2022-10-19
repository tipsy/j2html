package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IMax<T extends Tag<T>> extends IInstance<T> {
    default T withMax(final String max_) {
        return self().attr("max", max_);
    }

    default T withCondMax(final boolean enable, final String max_) {
        if (enable) {
            self().attr("max", max_);
        }
        return self();
    }
}
