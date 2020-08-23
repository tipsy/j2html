package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IHeaders<T extends Tag> extends IInstance<T> {
    default T withHeaders(final String headers_) {
        get().attr("headers", headers_);
        return get();
    }
}
