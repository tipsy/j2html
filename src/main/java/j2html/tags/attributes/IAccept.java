package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IAccept<T extends Tag> extends IInstance<T> {
    default T withAccept(final String accept_) {
        get().attr("accept", accept_);
        return get();
    }
}
