package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IOncuechange<T extends Tag<T>> extends IInstance<T> {
    default T withOncuechange(final String oncuechange_) {
        return self().attr("oncuechange", oncuechange_);
    }

    default T withCondOncuechange(final boolean enable, final String oncuechange_) {
        if (enable) {
            self().attr("oncuechange", oncuechange_);
        }
        return self();
    }
}
