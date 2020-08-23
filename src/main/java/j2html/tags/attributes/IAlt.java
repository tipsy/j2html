package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IAlt<T extends Tag> extends IInstance<T> {
    default T withAlt(final String alt_) {
        get().attr("alt", alt_);
        return get();
    }
}
