package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IOnpopstate<T extends Tag<T>> extends IInstance<T> {
    default T withOnpopstate(final String onpopstate_) {
        return self().attr("onpopstate", onpopstate_);
    }

    default T withCondOnpopstate(final boolean enable, final String onpopstate_) {
        if (enable) {
            self().attr("onpopstate", onpopstate_);
        }
        return self();
    }
}
