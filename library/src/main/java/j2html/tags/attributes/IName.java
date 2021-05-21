package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IName<T extends Tag<T>> extends IInstance<T> {
    default T withName(final String name_) {
        return self().attr("name", name_);
    }

    default T withCondName(final boolean enable, final String name_) {
        if (enable) {
            self().attr("name", name_);
        }
        return self();
    }
}
