package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IOncanplaythrough<T extends Tag> extends IInstance<T> {
    default T withOncanplaythrough(final String oncanplaythrough_) {
        get().attr("oncanplaythrough", oncanplaythrough_);
        return get();
    }
}
