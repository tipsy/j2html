package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IPattern<T extends Tag> extends IInstance<T> {
    default T withPattern(final String pattern_) {
        get().attr("pattern", pattern_);
        return get();
    }
}
