package j2html.tags.generators;

final class AttrD {
    //attribute descriptor

    public final String attr;

    //the html tags that this attribute can be used on
    public final String[] tags;

    public AttrD(final String attr){
        this.attr = attr;
        this.tags = new String[]{};
    }

    public AttrD(final String attr, final String... tags) {
        this.attr = attr;
        this.tags = tags;
    }
}
