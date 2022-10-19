package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IFor<T extends Tag<T>> extends IInstance<T> {
    default T withFor(final String for_) {
        return self().attr("for", for_);
    }

    default T withCondFor(final boolean enable, final String for_) {
        if (enable) {
            self().attr("for", for_);
        }
        return self();
    }
}
