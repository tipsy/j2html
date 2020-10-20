package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IForm<T extends Tag> extends IInstance<T> {
    default T withForm(final String form_) {
        get().attr("form", form_);
        return get();
    }

    default T withCondForm(final boolean enable, final String form_) {
        if (enable) {
            get().attr("form", form_);
        }
        return get();
    }
}
