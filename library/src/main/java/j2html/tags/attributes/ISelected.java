package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface ISelected<T extends Tag> extends IInstance<T> {
    default T isSelected() {
        get().attr("selected");
        return get();
    }

    default T withCondSelected(final boolean enable) {
        if (enable) {
            get().attr("selected");
        }
        return get();
    }
}
