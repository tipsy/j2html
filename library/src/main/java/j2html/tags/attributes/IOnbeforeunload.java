package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IOnbeforeunload<T extends Tag<T>> extends IInstance<T> {
    default T withOnbeforeunload(final String onbeforeunload_) {
        return self().attr("onbeforeunload", onbeforeunload_);
    }

    default T withCondOnbeforeunload(final boolean enable, final String onbeforeunload_) {
        if (enable) {
            self().attr("onbeforeunload", onbeforeunload_);
        }
        return self();
    }
}
