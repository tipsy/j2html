package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IAction<T extends Tag> extends IInstance<T> {
    default T withAction(final String action_) {
        get().attr("action", action_);
        return get();
    }

    default T withCondAction(final boolean enable, final String action_) {
        if (enable) {
            get().attr("action", action_);
        }
        return get();
    }
}
