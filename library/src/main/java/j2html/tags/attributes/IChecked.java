package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IChecked<T extends Tag> extends IInstance<T> {
    default T isChecked() {
        get().attr("checked");
        return get();
    }

    default T withCondChecked(final boolean enable) {
        if (enable) {
            get().attr("checked");
        }
        return get();
    }
}
