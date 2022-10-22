package j2html.tags.attributes;

import j2html.tags.Tag;
import j2html.tags.IInstance;

public interface INonce<T extends Tag<T>> extends IInstance<T>  {
default T isNonce() {self().attr("nonce");
return self();
}
default T withCondNonce(final boolean enable) {if (enable){
self().attr("nonce");
}
return self();
}
}
