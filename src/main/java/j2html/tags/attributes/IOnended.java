package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IOnended<T extends Tag> extends IInstance<T> {
    default T withOnended(final String onended_) {
        get().attr("onended", onended_);
        return get();
    }
}
