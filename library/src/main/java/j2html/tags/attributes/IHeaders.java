package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IHeaders<T extends Tag> extends IInstance<T> {
    default T withHeaders(final String headers_) {
        get().attr("headers", headers_);
        return get();
    }

    default T withCondHeaders(final boolean enable, final String headers_) {
        if (enable) {
            get().attr("headers", headers_);
        }
        return get();
    }
}
