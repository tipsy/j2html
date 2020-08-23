package j2html.tags.attributes;

import j2html.tags.Tag;

public interface ICite<T extends Tag> extends IInstance<T> {
    default T withCite(final String cite_) {
        get().attr("cite", cite_);
        return get();
    }
}
