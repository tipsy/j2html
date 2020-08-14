package j2html.tags.specialized;

import j2html.tags.ContainerTag;
import j2html.tags.Tag;

import java.io.IOException;
import java.util.Optional;

public final class HtmlTag extends Tag<HtmlTag> {

    private final Optional<HeadTag> head;
    private final Optional<BodyTag> body;

    public HtmlTag(){
        super("html");

        head = Optional.empty();
        body = Optional.empty();
    }

    public HtmlTag(HeadTag head){
        super("html");
        this.head = Optional.of(head);
        this.body = Optional.empty();
    }

    public HtmlTag(BodyTag body){
        super("html");
        this.head = Optional.empty();
        this.body = Optional.of(body);
    }

    public HtmlTag(HeadTag head, BodyTag body){
        super("html");
        this.head = Optional.of(head);
        this.body = Optional.of(body);
    }

    private ContainerTag makeContainerTagForRendering(){
        return new ContainerTag("html").with(
            this.head.orElse(null),
            this.body.orElse(null)
        );
    }

    @Override
    public String render() {
        return makeContainerTagForRendering().render();
    }

    @Override
    public void render(Appendable writer) throws IOException {
        makeContainerTagForRendering().render(writer);
    }

    @Override
    public void renderModel(Appendable writer, Object model) throws IOException {
        makeContainerTagForRendering().renderModel(writer, model);
    }

    public String renderFormatted() {
        return makeContainerTagForRendering().renderFormatted();
    }
}
