package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IMuted<T extends Tag> extends IInstance<T> {
    default T withMuted(final String muted_) {
        get().attr("muted", muted_);
        return get();
    }

    default T withCondMuted(final boolean enable, final String muted_) {
        if (enable) {
            get().attr("muted", muted_);
        }
        return get();
    }
}
