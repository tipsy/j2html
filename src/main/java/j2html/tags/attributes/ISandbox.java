package j2html.tags.attributes;

import j2html.tags.Tag;

public interface ISandbox<T extends Tag> extends IInstance<T> {
    default T withSandbox(final String sandbox_) {
        get().attr("sandbox", sandbox_);
        return get();
    }
}
