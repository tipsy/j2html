package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface ILabel<T extends Tag> extends IInstance<T> {
    default T withLabel(final String label_) {
        get().attr("label", label_);
        return get();
    }

    default T withCondLabel(final boolean enable, final String label_) {
        if (enable) {
            get().attr("label", label_);
        }
        return get();
    }
}
