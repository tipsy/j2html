package j2html.rendering;

import j2html.attributes.Attribute;
import j2html.tags.DomContent;
import org.junit.Test;

import java.io.IOException;

import static j2html.TagCreator.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class RenderingCompatabilityTest {

    @Test
    public void container_tags_continue_to_support_original_model_rendering_methods() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        div(div()).renderModel(stringBuilder, null);
        assertThat(stringBuilder.toString(), is("<div><div></div></div>"));
    }

    @Test
    public void empty_tags_continue_to_support_original_model_rendering_methods() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        input().withId("X").renderModel(stringBuilder, null);
        assertThat(stringBuilder.toString(), is("<input id=\"X\">"));
    }

    @Test
    public void text_continues_to_support_original_model_rendering_methods() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        text("abc").renderModel(stringBuilder, null);
        assertThat(stringBuilder.toString(), is("abc"));
    }

    @Test
    public void unescaped_text_continues_to_support_original_model_rendering_methods() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        rawHtml("abc").renderModel(stringBuilder, null);
        assertThat(stringBuilder.toString(), is("abc"));
    }

    @Test
    public void client_classes_which_implement_dom_rendering_continue_to_work_with_html_builder() throws IOException {
        // Simulate a client class which can be injected into ContainerTags as
        // regular DomContent.  Those client classes should continue to work
        // even though HtmlBuilders are the newer approach to rendering.
        DomContent dom = div(
            new DomContent() {
                @Override
                public void renderModel(Appendable writer, Object model) throws IOException {
                    writer.append(String.valueOf(model));
                }
            }
        );

        // Default rendering assumes a null model. Ensure that
        // convenience methods continue to work.
        assertThat(dom.render(), is("<div>null</div>"));

        StringBuilder stringBuilder = new StringBuilder();
        dom.render(stringBuilder);
        assertThat(stringBuilder.toString(), is("<div>null</div>"));

        stringBuilder = new StringBuilder();
        dom.renderModel(stringBuilder, "victory");
        assertThat(stringBuilder.toString(), is("<div>victory</div>"));

        // Rendering with an HtmlBuilder defers to the original methods.
        assertThat(
            dom.render(FlatHtml.inMemory(), "success").toString(),
            is("<div>success</div>")
        );
    }

    @Test
    public void client_classes_which_implement_attribute_rendering_continue_to_work_with_Attribute_builder() throws IOException {
        Attribute mock = new MockAttribute("XXX", "fail");

        // Stand-alone rendering.
        assertThat(mock.render(), is(" mock=\"null\""));

        StringBuilder stringBuilder = new StringBuilder();
        mock.render(stringBuilder);
        assertThat(stringBuilder.toString(), is(" mock=\"null\""));

        assertThat(
            mock.render(FlatHtml.inMemory(), "success").toString(),
            is(" mock=\"success\"")
        );

        // In empty tags.
        assertThat(
            input().attr(mock).render(FlatHtml.inMemory(), "success").toString(),
            is("<input mock=\"success\">")
        );

        // In container tags.
        assertThat(
            div().attr(mock).render(FlatHtml.inMemory(), "success").toString(),
            is("<div mock=\"success\"></div>")
        );
    }

    private final class MockAttribute extends Attribute {

        public MockAttribute(String name, String value) {
            super(name, value);
        }

        @Override
        public void renderModel(Appendable writer, Object model) throws IOException {
            writer.append(" mock=\"").append(String.valueOf(model)).append("\"");
        }
    }
}
