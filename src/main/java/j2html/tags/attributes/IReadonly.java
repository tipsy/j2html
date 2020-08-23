package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IReadonly<T extends Tag> extends IInstance<T> {
    default T withReadonly(final String readonly_) {
        get().attr("readonly", readonly_);
        return get();
    }
}
