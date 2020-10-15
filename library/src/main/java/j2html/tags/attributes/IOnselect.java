package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IOnselect<T extends Tag> extends IInstance<T> {
    default T withOnselect(final String onselect_) {
        get().attr("onselect", onselect_);
        return get();
    }
}
