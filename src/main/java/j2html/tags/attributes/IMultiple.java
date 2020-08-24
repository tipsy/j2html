package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IMultiple<T extends Tag> extends IInstance<T> {
    default T withMultiple(final String multiple_) {
        get().attr("multiple", multiple_);
        return get();
    }

    default T withCondMultiple(final boolean enable, final String multiple_) {
        if (enable) {
            get().attr("multiple", multiple_);
        }
        return get();
    }
}