package j2html.tags.attributes;

import j2html.tags.Tag;

public interface ISelected<T extends Tag> extends IInstance<T> {
    default T withSelected(final String selected_) {
        get().attr("selected", selected_);
        return get();
    }
}
