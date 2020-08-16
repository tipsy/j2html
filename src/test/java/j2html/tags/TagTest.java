package j2html.tags;

import j2html.Config;
import j2html.model.DynamicHrefAttribute;
import java.io.File;
import java.io.FileWriter;

import j2html.tags.specialized.HtmlTag;
import org.junit.Test;
import static j2html.TagCreator.body;
import static j2html.TagCreator.div;
import static j2html.TagCreator.footer;
import static j2html.TagCreator.header;
import static j2html.TagCreator.html;
import static j2html.TagCreator.iff;
import static j2html.TagCreator.img;
import static j2html.TagCreator.input;
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

        HtmlTag complexTestTag = html(
            body(
                header(),
                main().with(p("Main stuff...")),
                footer().condWith(1 == 1, p("Conditional with!"))
            )
        );
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
    public void testSelfClosingTags() throws Exception {
        Config.closeEmptyTags = true;
        assertThat(img().withSrc("/test.png").render(), is("<img src=\"/test.png\"/>"));
        assertThat(input().withType("text").render(), is("<input type=\"text\"/>"));
        Config.closeEmptyTags = false;
    }

    @Test
    public void testFormattedTags() throws Exception { // better test in ComplexRenderTest.java
        assertThat(div(p("Hello")).renderFormatted(), is("<div>\n    <p>\n        Hello\n    </p>\n</div>\n"));
    }

    @Test
    public void testEquals() throws Exception {
        Tag tagOne = tag("p").withClass("class").withText("Test");
        Tag tagTwo = p("Test").withClass("class");
        assertThat(tagOne.equals(tagTwo), is(true));
    }

    @Test
    public void testAcceptObjectValueAttribute() throws Exception {
        ContainerTag complexTestTag = new ContainerTag("input")
            .attr("attr1", "value1")
            .attr("attr2", 2)
            .attr("attr3", null);
        String expectedResult = "<input attr1=\"value1\" attr2=\"2\" attr3>";
        assertThat(complexTestTag.renderOpenTag(), is(expectedResult));
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

    @Test
    public void testDynamicAttribute() throws Exception {
        ContainerTag testTagWithAttrValueNull = new ContainerTag("a").attr(new DynamicHrefAttribute());
        assertThat(testTagWithAttrValueNull.render(), is("<a href=\"/\"></a>"));
    }

    @Test
    public void testDynamicAttributeReplacement() throws Exception {
        ContainerTag testTagWithAttrValueNull = new ContainerTag("a").attr("href", "/link").attr(new DynamicHrefAttribute());
        assertThat(testTagWithAttrValueNull.render(), is("<a href=\"/\"></a>"));
    }

    @Test
    public void renderToFile() throws Exception {
        FileWriter fileWriter = new FileWriter("file.txt");
        div("Test").render(fileWriter);
        fileWriter.close();
        new File("file.txt").delete();
    }

}
