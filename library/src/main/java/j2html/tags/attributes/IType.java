package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IType<T extends Tag> extends IInstance<T> {
    default T withType(final String type_) {
        get().attr("type", type_);
        return get();
    }

    default T withCondType(final boolean enable, final String type_) {
        if (enable) {
            get().attr("type", type_);
        }
        return get();
    }
}
