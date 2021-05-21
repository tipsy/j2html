package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IMethod<T extends Tag<T>> extends IInstance<T> {
    default T withMethod(final String method_) {
        return self().attr("method", method_);
    }

    default T withCondMethod(final boolean enable, final String method_) {
        if (enable) {
            self().attr("method", method_);
        }
        return self();
    }
}
