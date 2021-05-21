package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface ISelected<T extends Tag<T>> extends IInstance<T> {
    default T isSelected() {
        self().attr("selected");
        return self();
    }

    default T withCondSelected(final boolean enable) {
        if (enable) {
            self().attr("selected");
        }
        return self();
    }
}
