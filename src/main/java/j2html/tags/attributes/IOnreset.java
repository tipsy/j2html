package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IOnreset<T extends Tag> extends IInstance<T> {
    default T withOnreset(final String onreset_) {
        get().attr("onreset", onreset_);
        return get();
    }
}
