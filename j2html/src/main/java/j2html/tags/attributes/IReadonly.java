package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IReadonly<T extends Tag<T>> extends IInstance<T> {
    default T isReadonly() {
        self().attr("readonly");
        return self();
    }

    default T withCondReadonly(final boolean enable) {
        if (enable) {
            self().attr("readonly");
        }
        return self();
    }
}
