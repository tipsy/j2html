package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IAccesskey<T extends Tag> extends IInstance<T> {
    default T withAccesskey(final String accesskey_) {
        get().attr("accesskey", accesskey_);
        return get();
    }
}
