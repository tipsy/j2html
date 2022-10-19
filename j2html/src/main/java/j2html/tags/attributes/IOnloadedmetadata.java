package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IOnloadedmetadata<T extends Tag<T>> extends IInstance<T> {
    default T withOnloadedmetadata(final String onloadedmetadata_) {
        return self().attr("onloadedmetadata", onloadedmetadata_);
    }

    default T withCondOnloadedmetadata(final boolean enable, final String onloadedmetadata_) {
        if (enable) {
            self().attr("onloadedmetadata", onloadedmetadata_);
        }
        return self();
    }
}
