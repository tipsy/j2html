package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface ISize<T extends Tag<T>> extends IInstance<T> {
    default T withSize(final String size_) {
        return self().attr("size", size_);
    }

    default T withCondSize(final boolean enable, final String size_) {
        if (enable) {
            self().attr("size", size_);
        }
        return self();
    }
}
