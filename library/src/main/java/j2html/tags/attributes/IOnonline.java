package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IOnonline<T extends Tag> extends IInstance<T> {
    default T withOnonline(final String ononline_) {
        get().attr("ononline", ononline_);
        return get();
    }

    default T withCondOnonline(final boolean enable, final String ononline_) {
        if (enable) {
            get().attr("ononline", ononline_);
        }
        return get();
    }
}
