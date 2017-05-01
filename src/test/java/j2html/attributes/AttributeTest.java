package j2html.attributes;

import j2html.printer.ConfigurablePrinter;
import org.junit.Test;

import j2html.tags.ContainerTag;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AttributeTest {

    @Test
    public void testRender() throws Exception {
        Attribute attributeWithValue = new Attribute("href", "http://example.com");
        assertThat(attributeWithValue.render(new ConfigurablePrinter()), is(" href=\"http://example.com\""));

        Attribute attribute = new Attribute("required", null);
        assertThat(attribute.render(new ConfigurablePrinter()), is(" required"));

        Attribute nullAttribute = new Attribute(null, null);
        assertThat(nullAttribute.render(new ConfigurablePrinter()), is(""));
    }

    @Test
    public void testSetAttribute() throws Exception {
        ContainerTag testTag = new ContainerTag("a");
        testTag.attr("href", "http://example.com");
        testTag.attr("href", "http://example.org");
        assertThat(testTag.render(), is("<a href=\"http://example.org\"></a>"));
    }

}
