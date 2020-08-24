package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IMedia<T extends Tag> extends IInstance<T> {
    default T withMedia(final String media_) {
        get().attr("media", media_);
        return get();
    }

    default T withCondMedia(final boolean enable, final String media_) {
        if (enable) {
            get().attr("media", media_);
        }
        return get();
    }
}
