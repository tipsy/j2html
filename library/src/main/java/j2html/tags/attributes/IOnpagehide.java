package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IOnpagehide<T extends Tag> extends IInstance<T> {
    default T withOnpagehide(final String onpagehide_) {
        get().attr("onpagehide", onpagehide_);
        return get();
    }

    default T withCondOnpagehide(final boolean enable, final String onpagehide_) {
        if (enable) {
            get().attr("onpagehide", onpagehide_);
        }
        return get();
    }
}
