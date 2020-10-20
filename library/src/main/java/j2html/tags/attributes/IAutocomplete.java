package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IAutocomplete<T extends Tag> extends IInstance<T> {
    default T isAutocomplete() {
        get().attr("autocomplete", "on");
        return get();
    }

    default T withCondAutocomplete(final boolean enable) {
        if (enable) {
            get().attr("autocomplete", "on");
        }
        return get();
    }
}
