package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IOptimum<T extends Tag> extends IInstance<T> {
    default T withOptimum(final String optimum_) {
        get().attr("optimum", optimum_);
        return get();
    }
}
