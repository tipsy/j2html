package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IMedia<T extends Tag<T>> extends IInstance<T> {
    default T withMedia(final String media_) {
        return self().attr("media", media_);
    }

    default T withCondMedia(final boolean enable, final String media_) {
        if (enable) {
            self().attr("media", media_);
        }
        return self();
    }
}
