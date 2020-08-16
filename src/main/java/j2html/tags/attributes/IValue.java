package j2html.tags.attributes;

import j2html.tags.Tag;


public interface IValue<T extends Tag> extends IInstance<T> {

    public default T withValue(String value){
        get().attr("value", value);
        return get();
    }
}
