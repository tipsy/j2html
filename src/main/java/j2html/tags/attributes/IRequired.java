package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IRequired<T extends Tag> extends IInstance<T> {
    default T withRequired(final String required_) {
        get().attr("required", required_);
        return get();
    }
}
