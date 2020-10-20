package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IMultiple<T extends Tag> extends IInstance<T> {
    default T isMultiple() {
        get().attr("multiple");
        return get();
    }

    default T withCondMultiple(final boolean enable) {
        if (enable) {
            get().attr("multiple");
        }
        return get();
    }
}
