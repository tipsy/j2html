package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IMethod<T extends Tag> extends IInstance<T> {
    default T withMethod(final String method_) {
        get().attr("method", method_);
        return get();
    }
}
