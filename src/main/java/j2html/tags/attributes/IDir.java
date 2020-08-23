package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IDir<T extends Tag> extends IInstance<T> {
    default T withDir(final String dir_) {
        get().attr("dir", dir_);
        return get();
    }
}
