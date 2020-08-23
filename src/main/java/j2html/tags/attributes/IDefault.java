package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IDefault<T extends Tag> extends IInstance<T> {
    default T withDefault(final String default_) {
        get().attr("default", default_);
        return get();
    }
}
