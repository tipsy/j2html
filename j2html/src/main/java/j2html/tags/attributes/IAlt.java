package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IAlt<T extends Tag<T>> extends IInstance<T> {
    default T withAlt(final String alt_) {
        return self().attr("alt", alt_);
    }

    default T withCondAlt(final boolean enable, final String alt_) {
        if (enable) {
            self().attr("alt", alt_);
        }
        return self();
    }
}
