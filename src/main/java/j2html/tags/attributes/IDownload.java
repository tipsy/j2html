package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IDownload<T extends Tag> extends IInstance<T> {
    default T withDownload(final boolean download_) {
        if (download_) {
            get().attr("download");
        }
        return get();
    }
}
