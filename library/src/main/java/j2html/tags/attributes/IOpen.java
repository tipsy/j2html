package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IOpen<T extends Tag> extends IInstance<T> {
    default T isOpen() {
        get().attr("open");
        return get();
    }

    default T withCondOpen(final boolean enable) {
        if (enable) {
            get().attr("open");
        }
        return get();
    }
}
