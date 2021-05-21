package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IList<T extends Tag<T>> extends IInstance<T> {
    default T withList(final String list_) {
        return self().attr("list", list_);
    }

    default T withCondList(final boolean enable, final String list_) {
        if (enable) {
            self().attr("list", list_);
        }
        return self();
    }
}
