package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IOnprogress<T extends Tag> extends IInstance<T> {
    default T withOnprogress(final String onprogress_) {
        get().attr("onprogress", onprogress_);
        return get();
    }

    default T withCondOnprogress(final boolean enable, final String onprogress_) {
        if (enable) {
            get().attr("onprogress", onprogress_);
        }
        return get();
    }
}
