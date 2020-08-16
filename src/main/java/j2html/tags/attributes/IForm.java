package j2html.tags.attributes;

import j2html.tags.EmptyTag;
import j2html.tags.Tag;

public interface IForm<T extends Tag> extends IInstance<T> {

    public default T withForm(String formId){
        get().attr("form",formId);
        return get();
    }
}
