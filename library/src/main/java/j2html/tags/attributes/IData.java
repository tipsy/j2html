package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IData<T extends Tag<T>> extends IInstance<T> {
    default T withData(final String data_) {
        return self().attr("data", data_);
    }

    default T withCondData(final boolean enable, final String data_) {
        if (enable) {
            self().attr("data", data_);
        }
        return self();
    }
}
