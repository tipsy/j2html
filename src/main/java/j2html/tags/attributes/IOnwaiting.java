package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IOnwaiting<T extends Tag> extends IInstance<T> {
    default T withOnwaiting(final String onwaiting_) {
        get().attr("onwaiting", onwaiting_);
        return get();
    }
}
