package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IOnload<T extends Tag<T>> extends IInstance<T> {
    default T withOnload(final String onload_) {
        return self().attr("onload", onload_);
    }

    default T withCondOnload(final boolean enable, final String onload_) {
        if (enable) {
            self().attr("onload", onload_);
        }
        return self();
    }
}
