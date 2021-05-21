package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IAccept<T extends Tag<T>> extends IInstance<T> {
    default T withAccept(final String accept_) {
        return self().attr("accept", accept_);
    }

    default T withCondAccept(final boolean enable, final String accept_) {
        if (enable) {
            self().attr("accept", accept_);
        }
        return self();
    }
}
