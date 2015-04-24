package j2html.test.attributes;

import j2html.src.attributes.Attribute;
import j2html.src.tags.ContainerTag;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class AttributeTest {

    @Test
    public void testRender() throws Exception {
        Attribute attributeWithValue = new Attribute("href", "http://example.com");
        assertTrue(attributeWithValue.render().equals(" href=\"http://example.com\""));

        Attribute attribute = new Attribute("required", null);
        assertTrue(attribute.render().equals(" required"));

        Attribute nullAttribute = new Attribute(null, null);
        assertTrue(nullAttribute.render().equals(""));
    }

    @Test
    public void testSetAttribute() throws Exception {
        ContainerTag testTag = new ContainerTag("a");
        testTag.setAttribute("href", "http://example.com");
        testTag.setAttribute("href", "http://example.org");
        assertTrue(testTag.render().equals("<a href=\"http://example.org\"></a>"));
    }

}