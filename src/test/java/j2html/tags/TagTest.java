package j2html.tags;

import org.junit.Test;

import static j2html.TagCreator.body;
import static j2html.TagCreator.div;
import static j2html.TagCreator.footer;
import static j2html.TagCreator.header;
import static j2html.TagCreator.html;
import static j2html.TagCreator.iff;
import static j2html.TagCreator.main;
import static j2html.TagCreator.p;
import static j2html.TagCreator.tag;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TagTest {

    @Test
    public void testRender() throws Exception {
        ContainerTag testTag = new ContainerTag("a");
        testTag.setAttribute("href", "http://example.com");
        assertThat(testTag.render(), is("<a href=\"http://example.com\"></a>"));

        ContainerTag complexTestTag = html().with(body().with(header(), main().with(p("Main stuff...")), footer().condWith(1 == 1, p("Conditional with!"))));
        String expectedResult = "<html><body><header></header><main><p>Main stuff...</p></main><footer><p>Conditional with!</p></footer></body></html>";
        assertThat(complexTestTag.render(), is((expectedResult)));
    }

    @Test
    public void testOpenTag() throws Exception {
        ContainerTag testTag = new ContainerTag("a");
        assertThat(testTag.renderOpenTag(), is("<a>"));

        ContainerTag complexTestTag = new ContainerTag("input");
        complexTestTag.withType("password").withId("password").withName("password").withPlaceholder("Password").isRequired();
        String expectedResult = "<input type=\"password\" id=\"password\" name=\"password\" placeholder=\"Password\" required>";
        assertThat(complexTestTag.renderOpenTag(), is(expectedResult));
    }

    @Test
    public void testCloseTag() throws Exception {
        ContainerTag testTag = new ContainerTag("a");
        assertThat(testTag.renderCloseTag(), is("</a>"));
    }

    @Test
    public void testEquals() throws Exception {
        Tag tagOne = tag("p").withClass("class").withText("Test");
        Tag tagTwo = p("Test").withClass("class");
        assertThat(tagOne.equals(tagTwo), is(true));
    }

    @Test
    public void testWithClasses() throws Exception {
        String expected = "<div class=\"c1 c2\"></div>";
        String actual = div().withClasses("c1", iff(1 == 1, "c2"), iff(1 == 2, "c3")).render();
        assertThat(actual, is(expected));
    }

    @Test
    public void testEmptyAttribute() throws Exception {
        ContainerTag testTagWithAttrValueNull = new ContainerTag("a").attr("attribute", null);
        assertThat(testTagWithAttrValueNull.render(), is("<a attribute></a>"));

        ContainerTag testTagAttrWithoutAttr = new ContainerTag("a").attr("attribute");
        assertThat(testTagAttrWithoutAttr.render(), is("<a attribute></a>"));
    }


}
