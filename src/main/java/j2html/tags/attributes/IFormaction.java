package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IFormaction<T extends Tag> extends IInstance<T> {
    default T withFormaction(final String formaction_) {
        get().attr("formaction", formaction_);
        return get();
    }

    default T withCondFormaction(final boolean enable, final String formaction_) {
        if (enable) {
            get().attr("formaction", formaction_);
        }
        return get();
    }
}
