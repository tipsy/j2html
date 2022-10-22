package j2html.tags.attributes;

import j2html.tags.Tag;
import j2html.tags.IInstance;

public interface IDisplaystyle<T extends Tag<T>> extends IInstance<T>  {
default T isDisplaystyle() {self().attr("displaystyle");
return self();
}
default T withCondDisplaystyle(final boolean enable) {if (enable){
self().attr("displaystyle");
}
return self();
}
}
