package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface ISpan<T extends Tag<T>> extends IInstance<T> {
    default T withSpan(final String span_) {
        return self().attr("span", span_);
    }

    default T withCondSpan(final boolean enable, final String span_) {
        if (enable) {
            self().attr("span", span_);
        }
        return self();
    }
}
