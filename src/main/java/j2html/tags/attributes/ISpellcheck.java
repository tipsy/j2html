package j2html.tags.attributes;

import j2html.tags.Tag;

public interface ISpellcheck<T extends Tag> extends IInstance<T> {
    default T withSpellcheck(final String spellcheck_) {
        get().attr("spellcheck", spellcheck_);
        return get();
    }
}
