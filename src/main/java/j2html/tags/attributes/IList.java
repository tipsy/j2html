package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IList<T extends Tag> extends IInstance<T> {
    default T withList(final String list_) {
        get().attr("list", list_);
        return get();
    }
}
