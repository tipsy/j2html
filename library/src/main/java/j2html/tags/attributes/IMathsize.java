package j2html.tags.attributes;

import j2html.tags.Tag;
import j2html.tags.IInstance;

public interface IMathsize<T extends Tag<T>> extends IInstance<T>  {
default T isMathsize() {self().attr("mathsize");
return self();
}
default T withCondMathsize(final boolean enable) {if (enable){
self().attr("mathsize");
}
return self();
}
}
