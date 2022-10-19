package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface ISandbox<T extends Tag<T>> extends IInstance<T> {
    default T isSandbox() {
        self().attr("sandbox");
        return self();
    }

    default T withCondSandbox(final boolean enable) {
        if (enable) {
            self().attr("sandbox");
        }
        return self();
    }
}
