package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IOncuechange<T extends Tag> extends IInstance<T> {
    default T withOncuechange(final String oncuechange_) {
        get().attr("oncuechange", oncuechange_);
        return get();
    }
}
