package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IAutofocus<T extends Tag> extends IInstance<T> {
    default T withAutofocus(final String autofocus_) {
        get().attr("autofocus", autofocus_);
        return get();
    }
}
