package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IRel<T extends Tag<T>> extends IInstance<T> {
    default T withRel(final String rel_) {
        return self().attr("rel", rel_);
    }

    default T withCondRel(final boolean enable, final String rel_) {
        if (enable) {
            self().attr("rel", rel_);
        }
        return self();
    }
}
