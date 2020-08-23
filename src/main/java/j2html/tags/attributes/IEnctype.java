package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IEnctype<T extends Tag> extends IInstance<T> {
    default T withEnctype(final String enctype_) {
        get().attr("enctype", enctype_);
        return get();
    }
}
