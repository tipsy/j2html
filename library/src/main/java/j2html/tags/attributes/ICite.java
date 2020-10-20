package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface ICite<T extends Tag> extends IInstance<T> {
    default T withCite(final String cite_) {
        get().attr("cite", cite_);
        return get();
    }

    default T withCondCite(final boolean enable, final String cite_) {
        if (enable) {
            get().attr("cite", cite_);
        }
        return get();
    }
}
