package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IOnloadedmetadata<T extends Tag> extends IInstance<T> {
    default T withOnloadedmetadata(final String onloadedmetadata_) {
        get().attr("onloadedmetadata", onloadedmetadata_);
        return get();
    }
}
