package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IColspan<T extends Tag> extends IInstance<T> {
    default T withColspan(final String colspan_) {
        get().attr("colspan", colspan_);
        return get();
    }
}
