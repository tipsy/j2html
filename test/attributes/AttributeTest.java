package j2html.test.attributes;

import j2html.src.attributes.Attribute;
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

}