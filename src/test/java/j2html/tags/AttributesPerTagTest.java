package j2html.tags;

import org.junit.Test;

import static j2html.TagCreator.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public final class AttributesPerTagTest {

    //This class should contain Tests that check
    //if the generated Interface Methods indeed produce the correct HTML for
    //their custom HTML Attributes.

    //TODO: WARNING: only testing .with$ATTR and .is$ATTR Methods, not the 'Cond' Variants

    @Test
    public void testAttributesPerTag1(){

        //TEST:
        //accept, action, alt, async,
        //autocomplete, autofocus,
        //autoplay, charset, checked
        assertThat(input().withAccept("image/*").render(), is("<input accept=\"image/*\">"));

        assertThat(form().withAction("/1/2").render(), is("<form action=\"/1/2\"></form>"));

        assertThat(img().withAlt("horse").render(), is("<img alt=\"horse\">"));

        assertThat(script().isAsync().render(), is("<script async></script>"));

        assertThat(form().isAutocomplete().render(), is("<form autocomplete=\"on\"></form>"));

        assertThat(input().isAutofocus().render(), is("<input autofocus>"));

        assertThat(video().isAutoplay().render(), is("<video autoplay></video>"));

        assertThat(script().withCharset("mycharset").render(), is("<script charset=\"mycharset\"></script>"));

        assertThat(input().isChecked().render(), is("<input checked>"));
    }

    //TODO: leaving the rest of the tests for the rest
    //of the custom attributes for later, or for other people
    //to possibly implement
}
