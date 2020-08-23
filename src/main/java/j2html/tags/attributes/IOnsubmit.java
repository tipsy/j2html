package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IOnsubmit<T extends Tag> extends IInstance<T> {
    default T withOnsubmit(final String onsubmit_) {
        get().attr("onsubmit", onsubmit_);
        return get();
    }
}
