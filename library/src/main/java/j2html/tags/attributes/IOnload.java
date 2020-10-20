package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IOnload<T extends Tag> extends IInstance<T> {
    default T withOnload(final String onload_) {
        get().attr("onload", onload_);
        return get();
    }

    default T withCondOnload(final boolean enable, final String onload_) {
        if (enable) {
            get().attr("onload", onload_);
        }
        return get();
    }
}
