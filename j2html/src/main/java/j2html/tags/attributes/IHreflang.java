package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IHreflang<T extends Tag<T>> extends IInstance<T> {
    default T withHreflang(final String hreflang_) {
        return self().attr("hreflang", hreflang_);
    }

    default T withCondHreflang(final boolean enable, final String hreflang_) {
        if (enable) {
            self().attr("hreflang", hreflang_);
        }
        return self();
    }
}
