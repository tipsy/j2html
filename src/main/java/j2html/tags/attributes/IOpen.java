package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IOpen<T extends Tag> extends IInstance<T> {
    default T withOpen(final String open_) {
        get().attr("open", open_);
        return get();
    }
}
