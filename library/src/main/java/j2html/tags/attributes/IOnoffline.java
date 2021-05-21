package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IOnoffline<T extends Tag<T>> extends IInstance<T> {
    default T withOnoffline(final String onoffline_) {
        return self().attr("onoffline", onoffline_);
    }

    default T withCondOnoffline(final boolean enable, final String onoffline_) {
        if (enable) {
            self().attr("onoffline", onoffline_);
        }
        return self();
    }
}
