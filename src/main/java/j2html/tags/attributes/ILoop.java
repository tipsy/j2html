package j2html.tags.attributes;

import j2html.tags.Tag;

public interface ILoop<T extends Tag> extends IInstance<T> {
    default T isLoop() {
        get().attr("loop");
        return get();
    }

    default T withCondLoop(final boolean enable) {
        if (enable) {
            get().attr("loop");
        }
        return get();
    }
}
