package j2html.tags.attributes;

import j2html.tags.IInstance;
import j2html.tags.Tag;

public interface IOnvolumechanged<T extends Tag<T>> extends IInstance<T> {
    default T withOnvolumechanged(final String onvolumechanged_) {
        return self().attr("onvolumechanged", onvolumechanged_);
    }

    default T withCondOnvolumechanged(final boolean enable, final String onvolumechanged_) {
        if (enable) {
            self().attr("onvolumechanged", onvolumechanged_);
        }
        return self();
    }
}
