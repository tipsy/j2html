package j2html.tags.attributes;

import j2html.tags.Tag;

public interface ISize<T extends Tag> extends IInstance<T> {
    default T withSize(final String size_) {
        get().attr("size", size_);
        return get();
    }
}
