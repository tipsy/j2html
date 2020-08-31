package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IOnpause<T extends Tag> extends IInstance<T> {
    default T withOnpause(final String onpause_) {
        get().attr("onpause", onpause_);
        return get();
    }

    default T withCondOnpause(final boolean enable, final String onpause_) {
        if (enable) {
            get().attr("onpause", onpause_);
        }
        return get();
    }
}
