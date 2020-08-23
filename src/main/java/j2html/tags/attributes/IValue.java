package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IValue<T extends Tag> extends IInstance<T> {
    default T withValue(final String value_) {
        get().attr("value", value_);
        return get();
    }
}
