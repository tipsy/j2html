package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IPreload<T extends Tag> extends IInstance<T> {
    default T withPreload(final String preload_) {
        get().attr("preload", preload_);
        return get();
    }
}
