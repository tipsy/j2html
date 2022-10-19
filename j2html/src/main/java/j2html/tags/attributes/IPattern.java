package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IPattern<T extends Tag<T>> extends IInstance<T> {
    default T withPattern(final String pattern_) {
        return self().attr("pattern", pattern_);
    }

    default T withCondPattern(final boolean enable, final String pattern_) {
        if (enable) {
            self().attr("pattern", pattern_);
        }
        return self();
    }
}
