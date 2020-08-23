package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IContent<T extends Tag> extends IInstance<T> {
    default T withContent(final String content_) {
        get().attr("content", content_);
        return get();
    }
}
