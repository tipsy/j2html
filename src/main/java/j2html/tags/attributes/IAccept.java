package j2html.tags.attributes;

import j2html.tags.EmptyTag;
import j2html.tags.Tag;


public interface IAccept<T extends Tag> extends IInstance<T> {

    public default T withAccept(String accept){
        get().attr("accept", accept);
        return get();
    }
}
