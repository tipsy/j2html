package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IOnsearch<T extends Tag> extends IInstance<T> {
    default T withOnsearch(final String onsearch_) {
        get().attr("onsearch", onsearch_);
        return get();
    }
}
