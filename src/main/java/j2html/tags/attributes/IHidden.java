package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IHidden<T extends Tag> extends IInstance<T> {
    default T withHidden(final String hidden_) {
        get().attr("hidden", hidden_);
        return get();
    }
}
