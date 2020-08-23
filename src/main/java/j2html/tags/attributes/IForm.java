package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IForm<T extends Tag> extends IInstance<T> {
    default T withForm(final String form_) {
        get().attr("form", form_);
        return get();
    }
}
