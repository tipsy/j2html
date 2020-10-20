package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IOnpopstate<T extends Tag> extends IInstance<T> {
    default T withOnpopstate(final String onpopstate_) {
        get().attr("onpopstate", onpopstate_);
        return get();
    }

    default T withCondOnpopstate(final boolean enable, final String onpopstate_) {
        if (enable) {
            get().attr("onpopstate", onpopstate_);
        }
        return get();
    }
}
