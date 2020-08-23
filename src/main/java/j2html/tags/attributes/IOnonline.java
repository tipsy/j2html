package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IOnonline<T extends Tag> extends IInstance<T> {
    default T withOnonline(final String ononline_) {
        get().attr("ononline", ononline_);
        return get();
    }
}
