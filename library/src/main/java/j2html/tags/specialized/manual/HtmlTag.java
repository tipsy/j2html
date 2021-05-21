package j2html.tags.specialized.manual;

import j2html.Config;
import j2html.attributes.Attribute;
import j2html.rendering.TagBuilder;
import j2html.rendering.FlatHtml;
import j2html.rendering.HtmlBuilder;
import j2html.rendering.IndentedHtml;
import j2html.tags.Tag;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Optional;

public final class HtmlTag extends Tag<HtmlTag> {

    private final Optional<HeadTag> head;
    private final Optional<BodyTag> body;

    public HtmlTag() {
        super("html");

        head = Optional.empty();
        body = Optional.empty();
    }

    public HtmlTag(HeadTag head) {
        super("html");
        this.head = Optional.of(head);
        this.body = Optional.empty();
    }

    public HtmlTag(BodyTag body) {
        super("html");
        this.head = Optional.empty();
        this.body = Optional.of(body);
    }

    public HtmlTag(HeadTag head, BodyTag body) {
        super("html");
        this.head = Optional.of(head);
        this.body = Optional.of(body);
    }

    @Override
    public <T extends Appendable> T render(HtmlBuilder<T> builder, Object model) throws IOException {
        TagBuilder tagBuilder = builder.appendStartTag("html");
        for(Attribute attribute : getAttributes()){
            attribute.render(tagBuilder, model);
        }
        tagBuilder.completeTag();

        if(head.isPresent()){
            head.get().render(builder, model);
        }

        if(body.isPresent()){
            body.get().render(builder, model);
        }

        builder.appendEndTag("html");

        return builder.output();
    }

    public String renderFormatted() {
        try {
            return render(IndentedHtml.into(new StringBuilder(), Config.global())).toString();
        }catch (IOException e){
            throw new UncheckedIOException(e);
        }
    }

    @Override
    @Deprecated
    public void renderModel(Appendable writer, Object model) throws IOException {
        HtmlBuilder<?> builder = (writer instanceof HtmlBuilder)
            ? (HtmlBuilder<?>) writer
            : FlatHtml.into(writer, Config.global());

        render(builder, model);
    }
}
