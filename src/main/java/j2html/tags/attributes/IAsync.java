package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IAsync<T extends Tag> extends IInstance<T> {
    default T withAsync(final String async_) {
        get().attr("async", async_);
        return get();
    }

    default T withCondAsync(final boolean enable, final String async_) {
        if (enable) {
            get().attr("async", async_);
        }
        return get();
    }
}
