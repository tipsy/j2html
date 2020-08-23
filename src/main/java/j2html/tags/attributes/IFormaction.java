package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IFormaction<T extends Tag> extends IInstance<T> {
    default T withFormaction(final String formaction_) {
        get().attr("formaction", formaction_);
        return get();
    }
}
