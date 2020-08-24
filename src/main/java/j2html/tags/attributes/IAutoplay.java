package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IAutoplay<T extends Tag> extends IInstance<T> {
    default T withAutoplay(final String autoplay_) {
        get().attr("autoplay", autoplay_);
        return get();
    }

    default T withCondAutoplay(final boolean enable, final String autoplay_) {
        if (enable) {
            get().attr("autoplay", autoplay_);
        }
        return get();
    }
}
