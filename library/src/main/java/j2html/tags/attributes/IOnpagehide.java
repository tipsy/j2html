package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IOnpagehide<T extends Tag<T>> extends IInstance<T> {
    default T withOnpagehide(final String onpagehide_) {
        return self().attr("onpagehide", onpagehide_);
    }

    default T withCondOnpagehide(final boolean enable, final String onpagehide_) {
        if (enable) {
            self().attr("onpagehide", onpagehide_);
        }
        return self();
    }
}
