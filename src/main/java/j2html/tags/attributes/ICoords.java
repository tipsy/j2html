package j2html.tags.attributes;

import j2html.tags.Tag;

public interface ICoords<T extends Tag> extends IInstance<T> {
    default T withCoords(final String coords_) {
        get().attr("coords", coords_);
        return get();
    }
}
