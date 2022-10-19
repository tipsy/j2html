package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IOnloadstart<T extends Tag<T>> extends IInstance<T> {
    default T withOnloadstart(final String onloadstart_) {
        return self().attr("onloadstart", onloadstart_);
    }

    default T withCondOnloadstart(final boolean enable, final String onloadstart_) {
        if (enable) {
            self().attr("onloadstart", onloadstart_);
        }
        return self();
    }
}
