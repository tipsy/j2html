package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IAutocomplete<T extends Tag<T>> extends IInstance<T> {
    default T isAutocomplete() {
        self().attr("autocomplete", "on");
        return self();
    }

    default T withCondAutocomplete(final boolean enable) {
        if (enable) {
            self().attr("autocomplete", "on");
        }
        return self();
    }
}
