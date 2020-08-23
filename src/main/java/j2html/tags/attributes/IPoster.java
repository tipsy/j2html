package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IPoster<T extends Tag> extends IInstance<T> {
    default T withPoster(final String poster_) {
        get().attr("poster", poster_);
        return get();
    }
}
