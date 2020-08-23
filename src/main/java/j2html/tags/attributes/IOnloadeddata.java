package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IOnloadeddata<T extends Tag> extends IInstance<T> {
    default T withOnloadeddata(final String onloadeddata_) {
        get().attr("onloadeddata", onloadeddata_);
        return get();
    }
}
