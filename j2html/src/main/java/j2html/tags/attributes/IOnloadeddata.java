package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IOnloadeddata<T extends Tag<T>> extends IInstance<T> {
    default T withOnloadeddata(final String onloadeddata_) {
        return self().attr("onloadeddata", onloadeddata_);
    }

    default T withCondOnloadeddata(final boolean enable, final String onloadeddata_) {
        if (enable) {
            self().attr("onloadeddata", onloadeddata_);
        }
        return self();
    }
}
