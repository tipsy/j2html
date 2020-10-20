package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IData<T extends Tag> extends IInstance<T> {
    default T withData(final String data_) {
        get().attr("data", data_);
        return get();
    }

    default T withCondData(final boolean enable, final String data_) {
        if (enable) {
            get().attr("data", data_);
        }
        return get();
    }
}
