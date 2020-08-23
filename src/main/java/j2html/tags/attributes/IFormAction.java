package j2html.tags.attributes;

import j2html.tags.Tag;


public interface IFormAction<T extends Tag> extends IInstance<T> {

    default T withFormAction(String formAction) {
        get().attr("formaction", formAction);
        return get();
    }
}
