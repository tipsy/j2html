package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IChecked<T extends Tag> extends IInstance<T> {
    default T withChecked(final String checked_) {
        get().attr("checked", checked_);
        return get();
    }
}
