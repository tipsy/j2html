package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface ITarget<T extends Tag<T>> extends IInstance<T> {
    default T withTarget(final String target_) {
        return self().attr("target", target_);
    }

    default T withCondTarget(final boolean enable, final String target_) {
        if (enable) {
            self().attr("target", target_);
        }
        return self();
    }
}
