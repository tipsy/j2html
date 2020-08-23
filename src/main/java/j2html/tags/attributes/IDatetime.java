package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IDatetime<T extends Tag> extends IInstance<T> {
    default T withDatetime(final String datetime_) {
        get().attr("datetime", datetime_);
        return get();
    }
}
