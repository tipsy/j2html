package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IRel<T extends Tag> extends IInstance<T> {
    default T withRel(final String rel_) {
        get().attr("rel", rel_);
        return get();
    }

    default T withCondRel(final boolean enable, final String rel_) {
        if (enable) {
            get().attr("rel", rel_);
        }
        return get();
    }
}
