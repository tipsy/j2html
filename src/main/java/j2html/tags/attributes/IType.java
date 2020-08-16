package j2html.tags.attributes;

import j2html.tags.Tag;


public interface IType<T extends Tag> extends IInstance<T> {

    public default T withType(String type){
        get().attr("type", type);
        return get();
    }
}
