package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IAsync<T extends Tag> extends IInstance<T> {
    default T isAsync() {
        get().attr("async");
        return get();
    }

    default T withCondAsync(final boolean enable) {
        if (enable) {
            get().attr("async");
        }
        return get();
    }
}
