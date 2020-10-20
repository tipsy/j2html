package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IRequired<T extends Tag> extends IInstance<T> {
    default T isRequired() {
        get().attr("required");
        return get();
    }

    default T withCondRequired(final boolean enable) {
        if (enable) {
            get().attr("required");
        }
        return get();
    }
}
