package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IUsemap<T extends Tag> extends IInstance<T> {
    default T withUsemap(final String usemap_) {
        get().attr("usemap", usemap_);
        return get();
    }
}
