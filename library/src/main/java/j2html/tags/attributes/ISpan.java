package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface ISpan<T extends Tag> extends IInstance<T> {
    default T withSpan(final String span_) {
        get().attr("span", span_);
        return get();
    }

    default T withCondSpan(final boolean enable, final String span_) {
        if (enable) {
            get().attr("span", span_);
        }
        return get();
    }
}
