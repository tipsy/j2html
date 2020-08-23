package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IDirname<T extends Tag> extends IInstance<T> {
    default T withDirname(final String dirname_) {
        get().attr("dirname", dirname_);
        return get();
    }
}
