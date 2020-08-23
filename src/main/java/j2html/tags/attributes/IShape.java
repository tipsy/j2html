package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IShape<T extends Tag> extends IInstance<T> {
    default T withShape(final String shape_) {
        get().attr("shape", shape_);
        return get();
    }
}
