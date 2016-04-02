package j2html.attributes;

import j2html.tags.*;
import org.junit.*;
import static org.junit.Assert.*;

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
        testTag.attr("href", "http://example.com");
        testTag.attr("href", "http://example.org");
        assertEquals(testTag.render(), "<a href=\"http://example.org\"></a>");
    }

}
