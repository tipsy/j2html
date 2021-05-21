package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IUsemap<T extends Tag<T>> extends IInstance<T> {
    default T withUsemap(final String usemap_) {
        return self().attr("usemap", usemap_);
    }

    default T withCondUsemap(final boolean enable, final String usemap_) {
        if (enable) {
            self().attr("usemap", usemap_);
        }
        return self();
    }
}
