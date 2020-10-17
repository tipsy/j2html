package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IOnscroll<T extends Tag> extends IInstance<T> {
    default T withOnscroll(final String onscroll_) {
        get().attr("onscroll", onscroll_);
        return get();
    }
}
