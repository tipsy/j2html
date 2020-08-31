package j2html.tags.generators;

final class AttrD {
    //attribute descriptor

    public final String attr;

    public final boolean hasArgument;

    //the html tags that this attribute can be used on
    public final String[] tags;

    public AttrD(final String attr, boolean hasArgument){
        this.attr = attr;
        this.hasArgument = hasArgument;
        this.tags = new String[]{};
    }

    public AttrD(final String attr, boolean hasArgument, final String... tags) {
        this.attr = attr;
        this.hasArgument = hasArgument;
        this.tags = tags;
    }
}
