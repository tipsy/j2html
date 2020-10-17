package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IAutoplay<T extends Tag> extends IInstance<T> {
    default T isAutoplay() {
        get().attr("autoplay");
        return get();
    }

    default T withCondAutoplay(final boolean enable) {
        if (enable) {
            get().attr("autoplay");
        }
        return get();
    }
}
