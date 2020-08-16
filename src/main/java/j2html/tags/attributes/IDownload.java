package j2html.tags.attributes;

import j2html.tags.Tag;


public interface IDownload<T extends Tag> extends IInstance<T> {

    public default T withDownload(){
        get().attr("download");
        return get();
    }
}
