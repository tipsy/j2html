package j2html.tags;

public class DisabledTag extends Tag<DisabledTag> {
    public DisabledTag() {
        super(null);
    }

    @Override
    public String render() {
        return "";
    }
}
