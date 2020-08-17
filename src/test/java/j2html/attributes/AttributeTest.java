package j2html.attributes;

import static j2html.TagCreator.p;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import j2html.Config;
import j2html.tags.ContainerTag;

public class AttributeTest {

    @Test
    public void testRender() throws Exception {
        Attribute attributeWithValue = new Attribute("href", "http://example.com");
        assertThat(attributeWithValue.render(), is(" href=\"http://example.com\""));

        Attribute attribute = new Attribute("required", null);
        assertThat(attribute.render(), is(" required"));

        Attribute nullAttribute = new Attribute(null, null);
        assertThat(nullAttribute.render(), is(""));
    }

    @Test
    public void testSetAttribute() throws Exception {
        ContainerTag testTag = new ContainerTag("a");
        testTag.attr("href", "http://example.com");
        testTag.attr("href", "http://example.org");
        assertThat(testTag.render(), is("<a href=\"http://example.org\"></a>"));
    }

    @Test
    public void attrShouldEscapeValue() {
    	ContainerTag p1 = p().withClass("bar\"baz");
    	assertEquals( "<p class=\"" + Config.textEscaper.escape("bar\"baz") + "\"></p>", p1.render());

    	ContainerTag p2 = p().withClass("aaa").withClass("bar\"baz");
    	assertEquals(  "<p class=\"" + Config.textEscaper.escape("bar\"baz") + "\"></p>" , p2.render());
    }
	
	
}
