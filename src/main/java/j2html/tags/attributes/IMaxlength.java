package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IMaxlength<T extends Tag> extends IInstance<T> {
    default T withMaxlength(final String maxlength_) {
        get().attr("maxlength", maxlength_);
        return get();
    }
}
