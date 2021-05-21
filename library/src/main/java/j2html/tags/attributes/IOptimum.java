package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IOptimum<T extends Tag<T>> extends IInstance<T> {
    default T withOptimum(final String optimum_) {
        return self().attr("optimum", optimum_);
    }

    default T withCondOptimum(final boolean enable, final String optimum_) {
        if (enable) {
            self().attr("optimum", optimum_);
        }
        return self();
    }
}
