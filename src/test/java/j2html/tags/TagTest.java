package j2html.tags;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static j2html.TagCreator.*;

public class TagTest {

    @Test
    public void testRender() throws Exception {
        ContainerTag testTag = new ContainerTag("a");
        testTag.setAttribute("href", "http://example.com");
        assertTrue(testTag.render().equals("<a href=\"http://example.com\"></a>"));

        ContainerTag complexTestTag = html().with(body().with(header(), main().with(p().withText("Main stuff...")), footer()));
        String expectedResult ="<html><body><header></header><main><p>Main stuff...</p></main><footer></footer></body></html>";
        assertTrue(complexTestTag.render().equals(expectedResult));
    }

    @Test
    public void testOpenTag() throws Exception {
        ContainerTag testTag = new ContainerTag("a");
        assertTrue(testTag.renderOpenTag().equals("<a>"));

        ContainerTag complexTestTag = new ContainerTag("input");
        complexTestTag.withType("password").withId("password").withName("password").withPlaceholder("Password").isRequired();
        String expectedResult = "<input type=\"password\" id=\"password\" name=\"password\" placeholder=\"Password\" required>";
        assertTrue(complexTestTag.renderOpenTag().equals(expectedResult));
    }

    @Test
    public void testCloseTag() throws Exception {
        ContainerTag testTag = new ContainerTag("a");
        assertTrue(testTag.renderCloseTag().equals("</a>"));
    }

}