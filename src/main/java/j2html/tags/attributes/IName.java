package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IName<T extends Tag> extends IInstance<T> {
    default T withName(final String name_) {
        get().attr("name", name_);
        return get();
    }
}
