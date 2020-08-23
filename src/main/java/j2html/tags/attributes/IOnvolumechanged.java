package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IOnvolumechanged<T extends Tag> extends IInstance<T> {
    default T withOnvolumechanged(final String onvolumechanged_) {
        get().attr("onvolumechanged", onvolumechanged_);
        return get();
    }
}
