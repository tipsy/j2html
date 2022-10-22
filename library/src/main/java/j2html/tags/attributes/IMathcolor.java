package j2html.tags.attributes;

import j2html.tags.Tag;
import j2html.tags.IInstance;

public interface IMathcolor<T extends Tag<T>> extends IInstance<T>  {
default T isMathcolor() {self().attr("mathcolor");
return self();
}
default T withCondMathcolor(final boolean enable) {if (enable){
self().attr("mathcolor");
}
return self();
}
}
