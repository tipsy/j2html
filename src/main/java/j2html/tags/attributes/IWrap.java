package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IWrap<T extends Tag> extends IInstance<T> {
    default T withWrap(final String wrap_) {
        get().attr("wrap", wrap_);
        return get();
    }
}
