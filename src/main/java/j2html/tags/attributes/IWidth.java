package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IWidth<T extends Tag> extends IInstance<T> {
    default T withWidth(final String width_) {
        get().attr("width", width_);
        return get();
    }
}
