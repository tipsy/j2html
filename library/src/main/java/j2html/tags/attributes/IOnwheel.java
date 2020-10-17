package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IOnwheel<T extends Tag> extends IInstance<T> {
    default T withOnwheel(final String onwheel_) {
        get().attr("onwheel", onwheel_);
        return get();
    }
}
