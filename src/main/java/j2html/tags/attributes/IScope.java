package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IScope<T extends Tag> extends IInstance<T> {
    default T withScope(final String scope_) {
        get().attr("scope", scope_);
        return get();
    }
}
