package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IOnended<T extends Tag<T>> extends IInstance<T> {
    default T withOnended(final String onended_) {
        return self().attr("onended", onended_);
    }

    default T withCondOnended(final boolean enable, final String onended_) {
        if (enable) {
            self().attr("onended", onended_);
        }
        return self();
    }
}
