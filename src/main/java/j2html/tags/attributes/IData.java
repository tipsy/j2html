package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IData<T extends Tag> extends IInstance<T> {
    default T withData(final String data_) {
        get().attr("data", data_);
        return get();
    }
}
