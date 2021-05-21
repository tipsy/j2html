package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IAction<T extends Tag<T>> extends IInstance<T> {
    default T withAction(final String action_) {
        return self().attr("action", action_);
    }

    default T withCondAction(final boolean enable, final String action_) {
        if (enable) {
            self().attr("action", action_);
        }
        return self();
    }
}
