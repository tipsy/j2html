package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IReadonly<T extends Tag> extends IInstance<T> {
    default T isReadonly() {
        get().attr("readonly");
        return get();
    }

    default T withCondReadonly(final boolean enable) {
        if (enable) {
            get().attr("readonly");
        }
        return get();
    }
}
