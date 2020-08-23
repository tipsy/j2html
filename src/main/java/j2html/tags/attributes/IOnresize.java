package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IOnresize<T extends Tag> extends IInstance<T> {
    default T withOnresize(final String onresize_) {
        get().attr("onresize", onresize_);
        return get();
    }
}
