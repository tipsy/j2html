package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IAlt<T extends Tag> extends IInstance<T> {
    default T withAlt(final String alt_) {
        get().attr("alt", alt_);
        return get();
    }

    default T withCondAlt(final boolean enable, final String alt_) {
        if (enable) {
            get().attr("alt", alt_);
        }
        return get();
    }
}
