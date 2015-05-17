package j2html.attributes;

import j2html.tags.ContainerTag;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class AttributeTest {

    @Test
    public void testRender() throws Exception {
        Attribute attributeWithValue = new Attribute("href", "http://example.com");
        assertEquals(attributeWithValue.render(), " href=\"http://example.com\"");

        Attribute attribute = new Attribute("required", null);
        assertEquals(attribute.render(), " required");

        Attribute nullAttribute = new Attribute(null, null);
        assertEquals(nullAttribute.render(), "");
    }

    @Test
    public void testSetAttribute() throws Exception {
        ContainerTag testTag = new ContainerTag("a");
        testTag.setAttribute("href", "http://example.com");
        testTag.setAttribute("href", "http://example.org");
        assertEquals(testTag.render(), "<a href=\"http://example.org\"></a>");
    }

}