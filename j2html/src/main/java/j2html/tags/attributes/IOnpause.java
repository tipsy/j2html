package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IOnpause<T extends Tag<T>> extends IInstance<T> {
    default T withOnpause(final String onpause_) {
        return self().attr("onpause", onpause_);
    }

    default T withCondOnpause(final boolean enable, final String onpause_) {
        if (enable) {
            self().attr("onpause", onpause_);
        }
        return self();
    }
}
