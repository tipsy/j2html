package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface ISrcdoc<T extends Tag> extends IInstance<T> {
    default T withSrcdoc(final String srcdoc_) {
        get().attr("srcdoc", srcdoc_);
        return get();
    }

    default T withCondSrcdoc(final boolean enable, final String srcdoc_) {
        if (enable) {
            get().attr("srcdoc", srcdoc_);
        }
        return get();
    }
}
