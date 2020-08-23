package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IStart<T extends Tag> extends IInstance<T> {
    default T withStart(final String start_) {
        get().attr("start", start_);
        return get();
    }
}
