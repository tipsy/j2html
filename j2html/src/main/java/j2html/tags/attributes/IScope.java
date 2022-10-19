package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IScope<T extends Tag<T>> extends IInstance<T> {
    default T withScope(final String scope_) {
        return self().attr("scope", scope_);
    }

    default T withCondScope(final boolean enable, final String scope_) {
        if (enable) {
            self().attr("scope", scope_);
        }
        return self();
    }
}
