package j2html.tags.attributes;

import j2html.tags.Tag;

public interface ISrc<T extends Tag> extends IInstance<T> {
    default T withSrc(final String src_) {
        get().attr("src", src_);
        return get();
    }
}
