package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IDownload<T extends Tag> extends IInstance<T> {
    default T isDownload() {
        get().attr("download");
        return get();
    }

    default T withCondDownload(final boolean enable) {
        if (enable) {
            get().attr("download");
        }
        return get();
    }
}
