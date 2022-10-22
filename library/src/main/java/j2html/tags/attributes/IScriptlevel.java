package j2html.tags.attributes;

import j2html.tags.Tag;
import j2html.tags.IInstance;

public interface IScriptlevel<T extends Tag<T>> extends IInstance<T>  {
default T isScriptlevel() {self().attr("scriptlevel");
return self();
}
default T withCondScriptlevel(final boolean enable) {if (enable){
self().attr("scriptlevel");
}
return self();
}
}
