package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IHref<T extends Tag<T>> extends IInstance<T> {
    default T withHref(final String href_) {
        return self().attr("href", href_);
    }

    default T withCondHref(final boolean enable, final String href_) {
        if (enable) {
            self().attr("href", href_);
        }
        return self();
    }
}
