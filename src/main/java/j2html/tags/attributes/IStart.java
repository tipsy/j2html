package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IStart<T extends Tag> extends IInstance<T> {
    default T withStart(final String start_) {
        get().attr("start", start_);
        return get();
    }

    default T withCondStart(final boolean enable, final String start_) {
        if (enable) {
            get().attr("start", start_);
        }
        return get();
    }
}
