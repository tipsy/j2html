package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IControls<T extends Tag> extends IInstance<T> {
    default T withControls(final String controls_) {
        get().attr("controls", controls_);
        return get();
    }

    default T withCondControls(final boolean enable, final String controls_) {
        if (enable) {
            get().attr("controls", controls_);
        }
        return get();
    }
}
