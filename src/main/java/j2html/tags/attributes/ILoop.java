package j2html.tags.attributes;

import j2html.tags.Tag;

public interface ILoop<T extends Tag> extends IInstance<T> {
    default T withLoop(final String loop_) {
        get().attr("loop", loop_);
        return get();
    }
}
