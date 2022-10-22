package j2html.tags.attributes;

import j2html.tags.Tag;
import j2html.tags.IInstance;

public interface IMathbackground<T extends Tag<T>> extends IInstance<T>  {
default T isMathbackground() {self().attr("mathbackground");
return self();
}
default T withCondMathbackground(final boolean enable) {if (enable){
self().attr("mathbackground");
}
return self();
}
}
