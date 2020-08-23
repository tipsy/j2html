package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IOnratechange<T extends Tag> extends IInstance<T> {
    default T withOnratechange(final String onratechange_) {
        get().attr("onratechange", onratechange_);
        return get();
    }
}
