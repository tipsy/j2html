package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IDisabled<T extends Tag> extends IInstance<T> {
    default T withDisabled(final boolean disabled_) {
        if (disabled_) {
            get().attr("disabled");
        }
        return get();
    }
}
