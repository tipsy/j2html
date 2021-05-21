package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IKind<T extends Tag<T>> extends IInstance<T> {
    default T withKind(final String kind_) {
        return self().attr("kind", kind_);
    }

    default T withCondKind(final boolean enable, final String kind_) {
        if (enable) {
            self().attr("kind", kind_);
        }
        return self();
    }
}
