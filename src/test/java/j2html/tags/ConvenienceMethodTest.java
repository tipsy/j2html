package j2html.tags;

import org.junit.Test;

import static j2html.TagCreator.*;
import static org.junit.Assert.assertEquals;

public class ConvenienceMethodTest {

    @Test
    public void testAllConvenienceMethods() throws Exception {
        assertEquals(input().isAutoComplete().render(), "<input autocomplete>");
        assertEquals(input().isAutoFocus().render(), "<input autofocus>");
        assertEquals(input().isHidden().render(), "<input hidden>");
        assertEquals(input().isRequired().render(), "<input required>");
        assertEquals(img().withAlt("An image").render(), "<img alt=\"An image\">");
        assertEquals(form().withAction("post").render(), "<form action=\"post\"></form>");
        assertEquals(meta().withCharset("UTF-8").render(), "<meta charset=\"UTF-8\">");
        assertEquals(div().withClass("test-class").render(), "<div class=\"test-class\"></div>");
        assertEquals(meta().withContent("Test Content").render(), "<meta content=\"Test Content\">");
        assertEquals(a().withHref("http://example.com").render(), "<a href=\"http://example.com\"></a>");
        assertEquals(div().withId("test-id").render(), "<div id=\"test-id\"></div>");
        assertEquals(div().withData("testdata", "test").render(), "<div data-testdata=\"test\"></div>");
        assertEquals(form().withMethod("get").render(), "<form method=\"get\"></form>");
        assertEquals(input().withName("test-name").render(), "<input name=\"test-name\">");
        assertEquals(input().withPlaceholder("test-placeholder").render(), "<input placeholder=\"test-placeholder\">");
        assertEquals(a().withTarget("_blank").render(), "<a target=\"_blank\"></a>");
        assertEquals(input().withType("email").render(), "<input type=\"email\">");
        assertEquals(link().withRel("stylesheet").render(), "<link rel=\"stylesheet\">");
        assertEquals(img().withSrc("/img/test.png").render(), "<img src=\"/img/test.png\">");
        assertEquals(input().withValue("test-value").render(), "<input value=\"test-value\">");
    }
}
