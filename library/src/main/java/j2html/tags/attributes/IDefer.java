package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IDefer<T extends Tag> extends IInstance<T> {
    default T isDefer() {
        get().attr("defer");
        return get();
    }

    default T withCondDefer(final boolean enable) {
        if (enable) {
            get().attr("defer");
        }
        return get();
    }
}
