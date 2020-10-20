package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IMax<T extends Tag> extends IInstance<T> {
    default T withMax(final String max_) {
        get().attr("max", max_);
        return get();
    }

    default T withCondMax(final boolean enable, final String max_) {
        if (enable) {
            get().attr("max", max_);
        }
        return get();
    }
}
