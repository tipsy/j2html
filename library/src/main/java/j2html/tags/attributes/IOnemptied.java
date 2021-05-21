package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IOnemptied<T extends Tag<T>> extends IInstance<T> {
    default T withOnemptied(final String onemptied_) {
        return self().attr("onemptied", onemptied_);
    }

    default T withCondOnemptied(final boolean enable, final String onemptied_) {
        if (enable) {
            self().attr("onemptied", onemptied_);
        }
        return self();
    }
}
