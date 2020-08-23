package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IOnblur<T extends Tag> extends IInstance<T> {
    default T withOnblur(final String onblur_) {
        get().attr("onblur", onblur_);
        return get();
    }
}
