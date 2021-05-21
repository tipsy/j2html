package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IOnprogress<T extends Tag<T>> extends IInstance<T> {
    default T withOnprogress(final String onprogress_) {
        return self().attr("onprogress", onprogress_);
    }

    default T withCondOnprogress(final boolean enable, final String onprogress_) {
        if (enable) {
            self().attr("onprogress", onprogress_);
        }
        return self();
    }
}
