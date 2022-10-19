package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IMaxlength<T extends Tag<T>> extends IInstance<T> {
    default T withMaxlength(final String maxlength_) {
        return self().attr("maxlength", maxlength_);
    }

    default T withCondMaxlength(final boolean enable, final String maxlength_) {
        if (enable) {
            self().attr("maxlength", maxlength_);
        }
        return self();
    }
}
