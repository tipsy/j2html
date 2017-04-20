package j2html.tags;

import java.util.Optional;

import org.junit.Test;

import j2html.attributes.Attribute;

import static j2html.TagCreator.a;
import static j2html.TagCreator.div;
import static j2html.TagCreator.form;
import static j2html.TagCreator.img;
import static j2html.TagCreator.input;
import static j2html.TagCreator.link;
import static j2html.TagCreator.meta;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ConvenienceMethodTest {

    @Test
    public void testAllConvenienceMethods() throws Exception {
        assertThat(input().isAutoComplete().render(), is("<input autocomplete>"));
        assertThat(input().isAutoFocus().render(), is("<input autofocus>"));
        assertThat(input().isHidden().render(), is("<input hidden>"));
        assertThat(input().isRequired().render(), is("<input required>"));
        assertThat(img().withAlt("An image").render(), is("<img alt=\"An image\">"));
        assertThat(form().withAction("post").render(), is("<form action=\"post\"></form>"));
        assertThat(meta().withCharset("UTF-8").render(), is("<meta charset=\"UTF-8\">"));
        assertThat(div().withClass("test-class").render(), is("<div class=\"test-class\"></div>"));
        assertThat(meta().withContent("Test Content").render(), is("<meta content=\"Test Content\">"));
        assertThat(a().withHref("http://example.com").render(), is("<a href=\"http://example.com\"></a>"));
        assertThat(div().withId("test-id").render(), is("<div id=\"test-id\"></div>"));
        assertThat(div().withData("testdata", "test").render(), is("<div data-testdata=\"test\"></div>"));
        assertThat(form().withMethod("get").render(), is("<form method=\"get\"></form>"));
        assertThat(input().withName("test-name").render(), is("<input name=\"test-name\">"));
        assertThat(input().withPlaceholder("test-placeholder").render(), is("<input placeholder=\"test-placeholder\">"));
        assertThat(a().withTarget("_blank").render(), is("<a target=\"_blank\"></a>"));
        assertThat(input().withType("email").render(), is("<input type=\"email\">"));
        assertThat(link().withRel("stylesheet").render(), is("<link rel=\"stylesheet\">"));
        assertThat(link().withRole("role").render(), is("<link role=\"role\">"));
        assertThat(img().withSrc("/img/test.png").render(), is("<img src=\"/img/test.png\">"));
        assertThat(div().withStyle("background:red;").render(), is("<div style=\"background:red;\"></div>"));
        assertThat(input().withValue("test-value").render(), is("<input value=\"test-value\">"));
        assertThat(input().attr(new Attribute("test","test")).render(), is("<input test=\"test\">"));
        assertThat(input().attr(Optional.of(new Attribute("test","test"))).render(), is("<input test=\"test\">"));
    }
}
