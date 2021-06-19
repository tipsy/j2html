package j2html.tags;

import j2html.Config;
import j2html.attributes.Attribute;
import j2html.rendering.DefaultHtmlBuilder;
import j2html.rendering.HtmlBuilder;
import j2html.rendering.TagBuilder;
import java.io.IOException;

public class EmptyTag<T extends EmptyTag<T>> extends Tag<T> {

    public EmptyTag(String tagName) {
        super(tagName);
        if (tagName == null) {
            throw new IllegalArgumentException("Illegal tag name: null");
        }
        if ("".equals(tagName)) {
            throw new IllegalArgumentException("Illegal tag name: \"\"");
        }
    }

    @Override
    public <A extends Appendable> A render(HtmlBuilder<A> builder, Object model) throws IOException {
        TagBuilder attrs = builder.appendEmptyTag(getTagName());
        for (Attribute attr : getAttributes()) {
            attr.render(attrs, model);
        }
        attrs.completeTag();
        return builder.output();
    }

    @Override
    @Deprecated
    public void renderModel(Appendable writer, Object model) throws IOException {
        HtmlBuilder<?> builder = (writer instanceof HtmlBuilder)
            ? (HtmlBuilder<?>) writer
            : DefaultHtmlBuilder.withConfig(Config.global()).into(writer);

        render(builder, model);
    }
}
