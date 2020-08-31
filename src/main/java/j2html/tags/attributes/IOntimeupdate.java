package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IOntimeupdate<T extends Tag> extends IInstance<T> {
    default T withOntimeupdate(final String ontimeupdate_) {
        get().attr("ontimeupdate", ontimeupdate_);
        return get();
    }

    default T withCondOntimeupdate(final boolean enable, final String ontimeupdate_) {
        if (enable) {
            get().attr("ontimeupdate", ontimeupdate_);
        }
        return get();
    }
}
