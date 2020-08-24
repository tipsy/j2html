package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IOnemptied<T extends Tag> extends IInstance<T> {
    default T withOnemptied(final String onemptied_) {
        get().attr("onemptied", onemptied_);
        return get();
    }

    default T withCondOnemptied(final boolean enable, final String onemptied_) {
        if (enable) {
            get().attr("onemptied", onemptied_);
        }
        return get();
    }
}
