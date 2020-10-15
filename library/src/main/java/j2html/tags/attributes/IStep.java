package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IStep<T extends Tag> extends IInstance<T> {
    default T withStep(final String step_) {
        get().attr("step", step_);
        return get();
    }

    default T withCondStep(final boolean enable, final String step_) {
        if (enable) {
            get().attr("step", step_);
        }
        return get();
    }
}
