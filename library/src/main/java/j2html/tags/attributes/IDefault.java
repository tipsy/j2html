package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IDefault<T extends Tag<T>> extends IInstance<T> {
    default T isDefault() {
        self().attr("default");
        return self();
    }

    default T withCondDefault(final boolean enable) {
        if (enable) {
            self().attr("default");
        }
        return self();
    }
}
