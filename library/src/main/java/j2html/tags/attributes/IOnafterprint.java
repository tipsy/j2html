package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IOnafterprint<T extends Tag<T>> extends IInstance<T> {
    default T withOnafterprint(final String onafterprint_) {
        return self().attr("onafterprint", onafterprint_);
    }

    default T withCondOnafterprint(final boolean enable, final String onafterprint_) {
        if (enable) {
            self().attr("onafterprint", onafterprint_);
        }
        return self();
    }
}
