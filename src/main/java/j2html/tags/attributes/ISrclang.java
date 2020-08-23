package j2html.tags.attributes;

import j2html.tags.Tag;

public interface ISrclang<T extends Tag> extends IInstance<T> {
    default T withSrclang(final String srclang_) {
        get().attr("srclang", srclang_);
        return get();
    }
}
