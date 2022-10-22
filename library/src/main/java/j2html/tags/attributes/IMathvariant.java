package j2html.tags.attributes;

import j2html.tags.Tag;
import j2html.tags.IInstance;

public interface IMathvariant<T extends Tag<T>> extends IInstance<T>  {
default T isMathvariant() {self().attr("mathvariant");
return self();
}
default T withCondMathvariant(final boolean enable) {if (enable){
self().attr("mathvariant");
}
return self();
}
}
