package j2html.tags.attributes;

import j2html.tags.Tag;

public interface ISandbox<T extends Tag> extends IInstance<T> {
    default T isSandbox() {
        get().attr("sandbox");
        return get();
    }

    default T withCondSandbox(final boolean enable) {
        if (enable) {
            get().attr("sandbox");
        }
        return get();
    }
}
