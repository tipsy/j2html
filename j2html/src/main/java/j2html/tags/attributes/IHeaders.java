package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IHeaders<T extends Tag<T>> extends IInstance<T> {
    default T withHeaders(final String headers_) {
        return self().attr("headers", headers_);
    }

    default T withCondHeaders(final boolean enable, final String headers_) {
        if (enable) {
            self().attr("headers", headers_);
        }
        return self();
    }
}
