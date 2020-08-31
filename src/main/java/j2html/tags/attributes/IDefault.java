package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IDefault<T extends Tag> extends IInstance<T> {
    default T isDefault() {
        get().attr("default");
        return get();
    }

    default T withCondDefault(final boolean enable) {
        if (enable) {
            get().attr("default");
        }
        return get();
    }
}
