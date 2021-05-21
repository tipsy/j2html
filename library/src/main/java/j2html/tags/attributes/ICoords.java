package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface ICoords<T extends Tag<T>> extends IInstance<T> {
    default T withCoords(final String coords_) {
        return self().attr("coords", coords_);
    }

    default T withCondCoords(final boolean enable, final String coords_) {
        if (enable) {
            self().attr("coords", coords_);
        }
        return self();
    }
}
