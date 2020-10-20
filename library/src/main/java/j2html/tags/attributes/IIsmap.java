package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IIsmap<T extends Tag> extends IInstance<T> {
    default T isIsmap() {
        get().attr("ismap");
        return get();
    }

    default T withCondIsmap(final boolean enable) {
        if (enable) {
            get().attr("ismap");
        }
        return get();
    }
}
