package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IContent<T extends Tag<T>> extends IInstance<T> {
    default T withContent(final String content_) {
        return self().attr("content", content_);
    }

    default T withCondContent(final boolean enable, final String content_) {
        if (enable) {
            self().attr("content", content_);
        }
        return self();
    }
}
