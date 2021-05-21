package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IPoster<T extends Tag<T>> extends IInstance<T> {
    default T withPoster(final String poster_) {
        return self().attr("poster", poster_);
    }

    default T withCondPoster(final boolean enable, final String poster_) {
        if (enable) {
            self().attr("poster", poster_);
        }
        return self();
    }
}
