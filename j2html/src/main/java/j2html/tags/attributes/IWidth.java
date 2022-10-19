package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IWidth<T extends Tag<T>> extends IInstance<T> {
    default T withWidth(final String width_) {
        return self().attr("width", width_);
    }

    default T withCondWidth(final boolean enable, final String width_) {
        if (enable) {
            self().attr("width", width_);
        }
        return self();
    }
}
