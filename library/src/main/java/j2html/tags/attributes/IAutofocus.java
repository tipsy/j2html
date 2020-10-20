package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IAutofocus<T extends Tag> extends IInstance<T> {
    default T isAutofocus() {
        get().attr("autofocus");
        return get();
    }

    default T withCondAutofocus(final boolean enable) {
        if (enable) {
            get().attr("autofocus");
        }
        return get();
    }
}
