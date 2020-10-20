package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IOncanplaythrough<T extends Tag> extends IInstance<T> {
    default T withOncanplaythrough(final String oncanplaythrough_) {
        get().attr("oncanplaythrough", oncanplaythrough_);
        return get();
    }

    default T withCondOncanplaythrough(final boolean enable, final String oncanplaythrough_) {
        if (enable) {
            get().attr("oncanplaythrough", oncanplaythrough_);
        }
        return get();
    }
}
