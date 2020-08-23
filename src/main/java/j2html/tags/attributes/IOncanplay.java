package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IOncanplay<T extends Tag> extends IInstance<T> {
    default T withOncanplay(final String oncanplay_) {
        get().attr("oncanplay", oncanplay_);
        return get();
    }
}
