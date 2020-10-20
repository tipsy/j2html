package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IKind<T extends Tag> extends IInstance<T> {
    default T withKind(final String kind_) {
        get().attr("kind", kind_);
        return get();
    }

    default T withCondKind(final boolean enable, final String kind_) {
        if (enable) {
            get().attr("kind", kind_);
        }
        return get();
    }
}
