package j2html.tags;

import org.junit.Test;

import static j2html.TagCreator.*;
import static org.junit.Assert.assertEquals;

public class TagTest {

    @Test
    public void testRender() throws Exception {
        ContainerTag testTag = new ContainerTag("a");
        testTag.setAttribute("href", "http://example.com");
        assertEquals(testTag.render(), "<a href=\"http://example.com\"></a>");

        ContainerTag complexTestTag = html().with(body().with(header(),main().with(p("Main stuff...")),footer().condWith(1 == 1,p("Conditional with!"))));
        String expectedResult ="<html><body><header></header><main><p>Main stuff...</p></main><footer><p>Conditional with!</p></footer></body></html>";
        assertEquals(complexTestTag.render(), (expectedResult));
    }

    @Test
    public void testOpenTag() throws Exception {
        ContainerTag testTag = new ContainerTag("a");
        assertEquals(testTag.renderOpenTag(), "<a>");

        ContainerTag complexTestTag = new ContainerTag("input");
        complexTestTag.withType("password").withId("password").withName("password").withPlaceholder("Password").isRequired();
        String expectedResult = "<input type=\"password\" id=\"password\" name=\"password\" placeholder=\"Password\" required>";
        assertEquals(complexTestTag.renderOpenTag(), expectedResult);
    }

    @Test
    public void testCloseTag() throws Exception {
        ContainerTag testTag = new ContainerTag("a");
        assertEquals(testTag.renderCloseTag(), "</a>");
    }

}
