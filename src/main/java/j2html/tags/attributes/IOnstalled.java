package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IOnstalled<T extends Tag> extends IInstance<T> {
    default T withOnstalled(final String onstalled_) {
        get().attr("onstalled", onstalled_);
        return get();
    }
}
