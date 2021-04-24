package j2html.tags;

import j2html.Config;
import j2html.attributes.Attribute;
import j2html.model.DynamicHrefAttribute;
import java.io.File;
import java.io.FileWriter;

import j2html.tags.specialized.manual.HtmlTag;
import org.junit.After;
import org.junit.Before;
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
import static org.junit.Assert.fail;

public class TagTest {

    @Before
    public void setUp(){
        Config.closeEmptyTags = false;
    }

    @After
    public void tearDown(){
        // Restore Config defaults.
        Config.closeEmptyTags = false;
    }

    // TODO Introduce a different concept for a sequence of tags, and require valid names for all Tags.

    @Test
    public void unnamed_containers_do_not_render_tags_or_attributes(){
        Tag tag = new ContainerTag(null).attr("xyz", "123");
        assertThat(tag.render(), is(""));
    }

    @Test
    public void unnamed_containers_render_children(){
        ContainerTag tag = new ContainerTag(null)
            .with(new EmptyTag("abc"))
            .with(new ContainerTag("def"))
            .withText("ghi");
        assertThat(tag.render(), is("<abc><def></def>ghi"));
    }

    @Test
    public void named_containers_without_children_only_render_tags_and_attributes(){
        Tag tag = new ContainerTag("abc").attr("xyz", "123");
        assertThat(tag.render(), is("<abc xyz=\"123\"></abc>"));
    }

    @Test
    public void populated_named_containers_render_tags_attributes_and_children(){
        Tag tag = new ContainerTag("abc")
            .with(new EmptyTag("def"))
            .with(new ContainerTag("ghi"))
            .withText("jkl")
            .attr("xyz", "123");
        assertThat(tag.render(), is("<abc xyz=\"123\"><def><ghi></ghi>jkl</abc>"));
    }

    @Test
    public void empty_tags_must_be_named(){
        try{
            new EmptyTag(null);
            fail("Exception was not thrown.");
        }catch (IllegalArgumentException e){
            assertThat(e.getMessage(), is("Illegal tag name: null"));
        }

        try{
            new EmptyTag("");
            fail("Exception was not thrown.");
        }catch (IllegalArgumentException e){
            assertThat(e.getMessage(), is("Illegal tag name: \"\""));
        }
    }

    @Test
    public void empty_tags_can_be_configured_to_self_close(){
        // By default they will not be self-closing.
        assertThat(new EmptyTag("xyz").render(), is("<xyz>"));

        Config.closeEmptyTags = true;
        assertThat(new EmptyTag("xyz").render(), is("<xyz/>"));
    }

    @Test
    public void attributes_are_rendered_in_the_order_that_they_are_defined(){
        Tag container = new ContainerTag("abc")
            .attr("a","A")
            .attr(new Attribute("b","B"))
            .attr("c");
        assertThat(container.render(), is("<abc a=\"A\" b=\"B\" c></abc>"));

        Tag tag = new EmptyTag("abc")
            .attr("c")
            .attr(new Attribute("b","B"))
            .attr("a","A");
        assertThat(tag.render(), is("<abc c b=\"B\" a=\"A\">"));
    }

    @Test
    public void testRender() {
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
    public void testFormattedTags() throws Exception { // better test in ComplexRenderTest.java
        assertThat(div(p("Hello")).renderFormatted(), is("<div>\n    <p>\n        Hello\n    </p>\n</div>\n"));
    }

    @Test
    public void testEquals() throws Exception {
        Tag tagOne = tag("p").withText("Test").withClass("class");
        Tag tagTwo = p("Test").withClass("class");
        assertThat(tagOne.equals(tagTwo), is(true));
    }

    @Test
    public void attribute_values_are_converted_to_strings() throws Exception {
        Tag container = new ContainerTag("abc")
            .attr("string", "value1")
            .attr("integer", 2)
            .attr("none", null);
        assertThat(container.render(), is("<abc string=\"value1\" integer=\"2\" none></abc>"));

        Tag tag = new EmptyTag("abc")
            .attr("string", "value1")
            .attr("integer", 2)
            .attr("none", null);
        assertThat(tag.render(), is("<abc string=\"value1\" integer=\"2\" none>"));
    }

    @Test
    public void testWithClasses() throws Exception {
        String expected = "<div class=\"c1 c2\"></div>";
        String actual = div().withClasses("c1", iff(1 == 1, "c2"), iff(1 == 2, "c3")).render();
        assertThat(actual, is(expected));
    }

    @Test
    public void testEmptyAttribute() throws Exception {
        Tag testTagWithAttrValueNull = new ContainerTag("a").attr("attribute", null);
        assertThat(testTagWithAttrValueNull.render(), is("<a attribute></a>"));

        Tag testTagAttrWithoutAttr = new ContainerTag("a").attr("attribute");
        assertThat(testTagAttrWithoutAttr.render(), is("<a attribute></a>"));
    }

    @Test
    public void testDynamicAttribute() throws Exception {
        Tag testTagWithAttrValueNull = new ContainerTag("a").attr(new DynamicHrefAttribute());
        assertThat(testTagWithAttrValueNull.render(), is("<a href=\"/\"></a>"));
    }

    @Test
    public void testDynamicAttributeReplacement() throws Exception {
        Tag testTagWithAttrValueNull = new ContainerTag("a").attr("href", "/link").attr(new DynamicHrefAttribute());
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
