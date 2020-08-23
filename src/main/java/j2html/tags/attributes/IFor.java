package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IFor<T extends Tag> extends IInstance<T> {
    default T withFor(final String for_) {
        get().attr("for", for_);
        return get();
    }
}
