package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IOnbeforeprint<T extends Tag> extends IInstance<T> {
    default T withOnbeforeprint(final String onbeforeprint_) {
        get().attr("onbeforeprint", onbeforeprint_);
        return get();
    }

    default T withCondOnbeforeprint(final boolean enable, final String onbeforeprint_) {
        if (enable) {
            get().attr("onbeforeprint", onbeforeprint_);
        }
        return get();
    }
}
