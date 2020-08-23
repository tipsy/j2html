package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IMedia<T extends Tag> extends IInstance<T> {
    default T withMedia(final String media_) {
        get().attr("media", media_);
        return get();
    }
}
