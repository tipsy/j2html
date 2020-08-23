package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IOndurationchange<T extends Tag> extends IInstance<T> {
    default T withOndurationchange(final String ondurationchange_) {
        get().attr("ondurationchange", ondurationchange_);
        return get();
    }
}
