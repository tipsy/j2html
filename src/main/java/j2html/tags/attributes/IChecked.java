package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IChecked<T extends Tag> extends IInstance<T> {
    default T withChecked(final boolean checked_) {
        if (checked_) {
            get().attr("checked");
        }
        return get();
    }
}
