package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IOnonline<T extends Tag<T>> extends IInstance<T> {
    default T withOnonline(final String ononline_) {
        return self().attr("ononline", ononline_);
    }

    default T withCondOnonline(final boolean enable, final String ononline_) {
        if (enable) {
            self().attr("ononline", ononline_);
        }
        return self();
    }
}
