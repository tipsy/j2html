package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IAutocomplete<T extends Tag> extends IInstance<T> {
    default T withAutocomplete(final String autocomplete_) {
        get().attr("autocomplete", autocomplete_);
        return get();
    }
}
