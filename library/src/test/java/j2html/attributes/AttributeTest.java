package j2html.attributes;

import j2html.tags.ContainerTag;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

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
    public void testSetDataAttribute() throws Exception {
        ContainerTag testTag = new ContainerTag("a");
        testTag.dataAttr("href", "http://example.com");
        testTag.dataAttr("href", "http://example.org");
        assertThat(testTag.render(), is("<a data-href=\"http://example.org\"></a>"));
    }

    @Test
    public void testAppendAttributeValue() throws Exception {
        ContainerTag testTag = new ContainerTag("a");
        testTag.appendAttrValue("class", null);
        testTag.appendAttrValue("class", "css1");
        testTag.appendAttrValue("class", "css2");
        assertThat(testTag.render(), is("<a class=\"css1 css2\"></a>"));
    }
    @Test
    public void testRemoveAttributeValue() throws Exception {
        ContainerTag testTag = new ContainerTag("a");
        testTag.appendAttrValue("class", "css1");
        testTag.appendAttrValue("class", "css2");
        testTag.appendAttrValue("class", "css3");
        assertThat(testTag.render(), is("<a class=\"css1 css2 css3\"></a>"));
        testTag.removeAttrValue("class", "css2");
        assertThat(testTag.render(), is("<a class=\"css1 css3\"></a>"));
    }
    @Test
    public void testRemoveAttributeValueFromEmpty() throws Exception {
        ContainerTag testTag = new ContainerTag("a");
        testTag.removeAttrValue("class", "css2");
        assertThat(testTag.render(), is("<a></a>"));
    }
    @Test
    public void testRemoveAttributeValueFromNotExisting() throws Exception {
        ContainerTag testTag = new ContainerTag("a");
        testTag.removeAttrValue("clazz", "css2");
        assertThat(testTag.render(), is("<a></a>"));
    }
}
