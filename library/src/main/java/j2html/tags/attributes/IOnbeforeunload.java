package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IOnbeforeunload<T extends Tag> extends IInstance<T> {
    default T withOnbeforeunload(final String onbeforeunload_) {
        get().attr("onbeforeunload", onbeforeunload_);
        return get();
    }

    default T withCondOnbeforeunload(final boolean enable, final String onbeforeunload_) {
        if (enable) {
            get().attr("onbeforeunload", onbeforeunload_);
        }
        return get();
    }
}
