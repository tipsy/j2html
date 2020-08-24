package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IIsmap<T extends Tag> extends IInstance<T> {
    default T withIsmap(final String ismap_) {
        get().attr("ismap", ismap_);
        return get();
    }

    default T withCondIsmap(final boolean enable, final String ismap_) {
        if (enable) {
            get().attr("ismap", ismap_);
        }
        return get();
    }
}
