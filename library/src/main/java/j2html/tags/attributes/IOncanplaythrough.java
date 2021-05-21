package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IOncanplaythrough<T extends Tag<T>> extends IInstance<T> {
    default T withOncanplaythrough(final String oncanplaythrough_) {
        return self().attr("oncanplaythrough", oncanplaythrough_);
    }

    default T withCondOncanplaythrough(final boolean enable, final String oncanplaythrough_) {
        if (enable) {
            self().attr("oncanplaythrough", oncanplaythrough_);
        }
        return self();
    }
}
