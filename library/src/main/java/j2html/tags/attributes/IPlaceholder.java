package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IPlaceholder<T extends Tag<T>> extends IInstance<T> {
    default T withPlaceholder(final String placeholder_) {
        return self().attr("placeholder", placeholder_);
    }

    default T withCondPlaceholder(final boolean enable, final String placeholder_) {
        if (enable) {
            self().attr("placeholder", placeholder_);
        }
        return self();
    }
}
