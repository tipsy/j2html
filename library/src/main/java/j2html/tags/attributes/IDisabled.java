package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IDisabled<T extends Tag> extends IInstance<T> {
    default T isDisabled() {
        get().attr("disabled");
        return get();
    }

    default T withCondDisabled(final boolean enable) {
        if (enable) {
            get().attr("disabled");
        }
        return get();
    }
}
