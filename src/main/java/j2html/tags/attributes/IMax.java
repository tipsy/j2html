package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IMax<T extends Tag> extends IInstance<T> {
    default T withMax(final String max_) {
        get().attr("max", max_);
        return get();
    }
}
