package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IOnpageshow<T extends Tag> extends IInstance<T> {
    default T withOnpageshow(final String onpageshow_) {
        get().attr("onpageshow", onpageshow_);
        return get();
    }

    default T withCondOnpageshow(final boolean enable, final String onpageshow_) {
        if (enable) {
            get().attr("onpageshow", onpageshow_);
        }
        return get();
    }
}
