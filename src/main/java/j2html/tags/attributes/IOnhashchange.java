package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IOnhashchange<T extends Tag> extends IInstance<T> {
    default T withOnhashchange(final String onhashchange_) {
        get().attr("onhashchange", onhashchange_);
        return get();
    }

    default T withCondOnhashchange(final boolean enable, final String onhashchange_) {
        if (enable) {
            get().attr("onhashchange", onhashchange_);
        }
        return get();
    }
}
