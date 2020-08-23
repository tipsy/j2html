package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IDefer<T extends Tag> extends IInstance<T> {
    default T withDefer(final String defer_) {
        get().attr("defer", defer_);
        return get();
    }
}
