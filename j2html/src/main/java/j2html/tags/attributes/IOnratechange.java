package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IOnratechange<T extends Tag<T>> extends IInstance<T> {
    default T withOnratechange(final String onratechange_) {
        return self().attr("onratechange", onratechange_);
    }

    default T withCondOnratechange(final boolean enable, final String onratechange_) {
        if (enable) {
            self().attr("onratechange", onratechange_);
        }
        return self();
    }
}
