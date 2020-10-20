package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface ICharset<T extends Tag> extends IInstance<T> {
    default T withCharset(final String charset_) {
        get().attr("charset", charset_);
        return get();
    }

    default T withCondCharset(final boolean enable, final String charset_) {
        if (enable) {
            get().attr("charset", charset_);
        }
        return get();
    }
}
