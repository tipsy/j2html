package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IHeight<T extends Tag> extends IInstance<T> {
    default T withHeight(final String height_) {
        get().attr("height", height_);
        return get();
    }

    default T withCondHeight(final boolean enable, final String height_) {
        if (enable) {
            get().attr("height", height_);
        }
        return get();
    }
}
