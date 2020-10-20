package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface ITarget<T extends Tag> extends IInstance<T> {
    default T withTarget(final String target_) {
        get().attr("target", target_);
        return get();
    }

    default T withCondTarget(final boolean enable, final String target_) {
        if (enable) {
            get().attr("target", target_);
        }
        return get();
    }
}
