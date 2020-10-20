package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface INovalidate<T extends Tag> extends IInstance<T> {
    default T isNovalidate() {
        get().attr("novalidate");
        return get();
    }

    default T withCondNovalidate(final boolean enable) {
        if (enable) {
            get().attr("novalidate");
        }
        return get();
    }
}
