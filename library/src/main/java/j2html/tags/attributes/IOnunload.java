package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IOnunload<T extends Tag> extends IInstance<T> {
    default T withOnunload(final String onunload_) {
        get().attr("onunload", onunload_);
        return get();
    }

    default T withCondOnunload(final boolean enable, final String onunload_) {
        if (enable) {
            get().attr("onunload", onunload_);
        }
        return get();
    }
}
