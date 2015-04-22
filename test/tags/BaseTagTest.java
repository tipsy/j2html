package j2html.test.tags;

import j2html.src.tags.Tag;
import org.junit.Test;

import static j2html.src.tags.TagCreator.*;
import static org.junit.Assert.assertTrue;

public class BaseTagTest {

    @Test
    public void testSetAttribute() throws Exception {
        Tag testTag = new Tag("a");
        testTag.setAttribute("href", "http://example.com");
        testTag.setAttribute("href", "http://example.org");
        assertTrue(testTag.render().equals("<a href=\"http://example.org\"></a>"));
    }

    @Test
    public void testRender() throws Exception {
        Tag testTag = new Tag("a");
        testTag.setAttribute("href", "http://example.com");
        assertTrue(testTag.render().equals("<a href=\"http://example.com\"></a>"));

        Tag complexTestTag = html().with(body().with(header(), main().with(p().withText("Main stuff...")), footer()));
        String expectedResult ="<html><body><header></header><main><p>Main stuff...</p></main><footer></footer></body></html>";
        assertTrue(complexTestTag.render().equals(expectedResult));
    }

    @Test
    public void testOpenTag() throws Exception {
        Tag testTag = new Tag("a");
        assertTrue(testTag.openTag().equals("<a>"));

        Tag complexTestTag = new Tag("input");
        complexTestTag.withType("password").withId("password").withName("password").withPlaceholder("Password").isRequired();
        String expectedResult = "<input type=\"password\" id=\"password\" name=\"password\" placeholder=\"Password\" required>";
        assertTrue(complexTestTag.openTag().equals(expectedResult));
    }

    @Test
    public void testCloseTag() throws Exception {
        Tag testTag = new Tag("a");
        assertTrue(testTag.closeTag().equals("</a>"));
    }
}