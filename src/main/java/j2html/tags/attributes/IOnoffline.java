package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IOnoffline<T extends Tag> extends IInstance<T> {
    default T withOnoffline(final String onoffline_) {
        get().attr("onoffline", onoffline_);
        return get();
    }

    default T withCondOnoffline(final boolean enable, final String onoffline_) {
        if (enable) {
            get().attr("onoffline", onoffline_);
        }
        return get();
    }
}
