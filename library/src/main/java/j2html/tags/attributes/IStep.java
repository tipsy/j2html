package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IStep<T extends Tag<T>> extends IInstance<T> {
    default T withStep(final String step_) {
        return self().attr("step", step_);
    }

    default T withCondStep(final boolean enable, final String step_) {
        if (enable) {
            self().attr("step", step_);
        }
        return self();
    }
}
