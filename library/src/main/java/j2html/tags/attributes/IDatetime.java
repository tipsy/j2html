package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IDatetime<T extends Tag> extends IInstance<T> {
    default T withDatetime(final String datetime_) {
        get().attr("datetime", datetime_);
        return get();
    }

    default T withCondDatetime(final boolean enable, final String datetime_) {
        if (enable) {
            get().attr("datetime", datetime_);
        }
        return get();
    }
}
