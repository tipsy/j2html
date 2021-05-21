package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IFormaction<T extends Tag<T>> extends IInstance<T> {
    default T withFormaction(final String formaction_) {
        return self().attr("formaction", formaction_);
    }

    default T withCondFormaction(final boolean enable, final String formaction_) {
        if (enable) {
            self().attr("formaction", formaction_);
        }
        return self();
    }
}
