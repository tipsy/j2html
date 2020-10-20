package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IScope<T extends Tag> extends IInstance<T> {
    default T withScope(final String scope_) {
        get().attr("scope", scope_);
        return get();
    }

    default T withCondScope(final boolean enable, final String scope_) {
        if (enable) {
            get().attr("scope", scope_);
        }
        return get();
    }
}
