package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface ISrcset<T extends Tag> extends IInstance<T> {
    default T withSrcset(final String srcset_) {
        get().attr("srcset", srcset_);
        return get();
    }

    default T withCondSrcset(final boolean enable, final String srcset_) {
        if (enable) {
            get().attr("srcset", srcset_);
        }
        return get();
    }
}
