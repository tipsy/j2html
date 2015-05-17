package j2html.tags;

import org.junit.Test;

import static junit.framework.Assert.assertTrue;
import static j2html.TagCreator.*;

public class ConvenienceMethodTest {

    @Test
    public void testAllConvenienceMethods() throws Exception {
        assertTrue(input().isAutoComplete().render().equals("<input autocomplete>"));
        assertTrue(input().isAutoFocus().render().equals("<input autofocus>"));
        assertTrue(input().isHidden().render().equals("<input hidden>"));
        assertTrue(input().isRequired().render().equals("<input required>"));
        assertTrue(img().withAlt("An image").render().equals("<img alt=\"An image\">"));
        assertTrue(form().withAction("post").render().equals("<form action=\"post\"></form>"));
        assertTrue(meta().withCharset("UTF-8").render().equals("<meta charset=\"UTF-8\">"));
        assertTrue(div().withClass("test-class").render().equals("<div class=\"test-class\"></div>"));
        assertTrue(meta().withContent("Test Content").render().equals("<meta content=\"Test Content\">"));
        assertTrue(a().withHref("http://example.com").render().equals("<a href=\"http://example.com\"></a>"));
        assertTrue(div().withId("test-id").render().equals("<div id=\"test-id\"></div>"));
        assertTrue(div().withData("testdata", "test").render().equals("<div data-testdata=\"test\"></div>"));
        assertTrue(form().withMethod("get").render().equals("<form method=\"get\"></form>"));
        assertTrue(input().withName("test-name").render().equals("<input name=\"test-name\">"));
        assertTrue(input().withPlaceholder("test-placeholder").render().equals("<input placeholder=\"test-placeholder\">"));
        assertTrue(a().withTarget("_blank").render().equals("<a target=\"_blank\"></a>"));
        assertTrue(input().withType("email").render().equals("<input type=\"email\">"));
        assertTrue(link().withRel("stylesheet").render().equals("<link rel=\"stylesheet\">"));
        assertTrue(img().withSrc("/img/test.png").render().equals("<img src=\"/img/test.png\">"));
        assertTrue(input().withValue("test-value").render().equals("<input value=\"test-value\">"));
    }
}
