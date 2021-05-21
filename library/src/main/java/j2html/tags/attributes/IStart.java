package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IStart<T extends Tag<T>> extends IInstance<T> {
    default T withStart(final String start_) {
        return self().attr("start", start_);
    }

    default T withCondStart(final boolean enable, final String start_) {
        if (enable) {
            self().attr("start", start_);
        }
        return self();
    }
}
