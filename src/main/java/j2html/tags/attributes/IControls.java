package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IControls<T extends Tag> extends IInstance<T> {
    default T isControls() {
        get().attr("controls");
        return get();
    }

    default T withCondControls(final boolean enable) {
        if (enable) {
            get().attr("controls");
        }
        return get();
    }
}
