package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IChecked<T extends Tag<T>> extends IInstance<T> {
    default T isChecked() {
        self().attr("checked");
        return self();
    }

    default T withCondChecked(final boolean enable) {
        if (enable) {
            self().attr("checked");
        }
        return self();
    }
}
