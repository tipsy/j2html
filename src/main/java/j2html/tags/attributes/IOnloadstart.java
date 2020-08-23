package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IOnloadstart<T extends Tag> extends IInstance<T> {
    default T withOnloadstart(final String onloadstart_) {
        get().attr("onloadstart", onloadstart_);
        return get();
    }
}
