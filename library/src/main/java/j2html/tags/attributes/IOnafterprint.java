package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IOnafterprint<T extends Tag> extends IInstance<T> {
    default T withOnafterprint(final String onafterprint_) {
        get().attr("onafterprint", onafterprint_);
        return get();
    }

    default T withCondOnafterprint(final boolean enable, final String onafterprint_) {
        if (enable) {
            get().attr("onafterprint", onafterprint_);
        }
        return get();
    }
}
