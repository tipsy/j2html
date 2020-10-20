package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IOntoggle<T extends Tag> extends IInstance<T> {
    default T withOntoggle(final String ontoggle_) {
        get().attr("ontoggle", ontoggle_);
        return get();
    }

    default T withCondOntoggle(final boolean enable, final String ontoggle_) {
        if (enable) {
            get().attr("ontoggle", ontoggle_);
        }
        return get();
    }
}
