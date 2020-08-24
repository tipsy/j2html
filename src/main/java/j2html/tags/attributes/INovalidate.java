package j2html.tags.attributes;

import j2html.tags.Tag;

public interface INovalidate<T extends Tag> extends IInstance<T> {
    default T withNovalidate(final String novalidate_) {
        get().attr("novalidate", novalidate_);
        return get();
    }

    default T withCondNovalidate(final boolean enable, final String novalidate_) {
        if (enable) {
            get().attr("novalidate", novalidate_);
        }
        return get();
    }
}
