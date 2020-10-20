package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface ISrclang<T extends Tag> extends IInstance<T> {
    default T withSrclang(final String srclang_) {
        get().attr("srclang", srclang_);
        return get();
    }

    default T withCondSrclang(final boolean enable, final String srclang_) {
        if (enable) {
            get().attr("srclang", srclang_);
        }
        return get();
    }
}
