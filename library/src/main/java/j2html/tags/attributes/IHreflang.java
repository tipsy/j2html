package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IHreflang<T extends Tag> extends IInstance<T> {
    default T withHreflang(final String hreflang_) {
        get().attr("hreflang", hreflang_);
        return get();
    }

    default T withCondHreflang(final boolean enable, final String hreflang_) {
        if (enable) {
            get().attr("hreflang", hreflang_);
        }
        return get();
    }
}
