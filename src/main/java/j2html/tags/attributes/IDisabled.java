package j2html.tags.attributes;

import j2html.tags.Tag;

public interface IDisabled<T extends Tag> extends IInstance<T> {

    public default T withDisabled(boolean isDisabled){
        if(isDisabled){
            get().attr("disabled");
        }
        return get();
    }

}
