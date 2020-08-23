package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IOnafterprint<T extends Tag> extends IInstance<T> {
    default T withOnafterprint(final String onafterprint_) {
        get().attr("onafterprint", onafterprint_);
        return get();
    }
}
