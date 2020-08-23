package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IRel<T extends Tag> extends IInstance<T> {
    default T withRel(final String rel_) {
        get().attr("rel", rel_);
        return get();
    }
}
