package j2html.attributes;

import org.junit.Test;

import static j2html.TagCreator.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AttrShortFormTest {
    
    @Test
    public void addTo_emptyTag() throws Exception {
        String expected = "<input id=\"some-id\" class=\"some-class\">";
        String actual = input(attrs("#some-id.some-class")).render();
        assertThat(actual, is(expected));
    }

    @Test
    public void addTo_containerTag() throws Exception {
        String expected = "<div id=\"some-id\" class=\"some-class\"></div>";
        String actual = div(attrs("#some-id.some-class")).render();
        assertThat(actual, is(expected));
    }

    @Test
    public void addTo_justId() throws Exception {
        String expected = "<div id=\"some-id\"></div>";
        String actual = div(attrs("#some-id")).render();
        assertThat(actual, is(expected));
    }

    @Test
    public void addTo_justClass() throws Exception {
        String expected = "<div class=\"some-class\"></div>";
        String actual = div(attrs(".some-class")).render();
        assertThat(actual, is(expected));
    }

    @Test
    public void addTo_addTwoClasses() throws Exception {
        String expected = "<div class=\"some-class some-other-class\"></div>";
        String actual = div(attrs(".some-class.some-other-class")).render();
        assertThat(actual, is(expected));
    }

    @Test(expected = IllegalArgumentException.class)
    public void addTo_stringWithoutIdOrClass() throws Exception {
        div(attrs("some-class")).render();
    }

    @Test(expected = IllegalArgumentException.class)
    public void addTo_stringWithTwoIds() throws Exception {
        div(attrs("#id1#id2")).render();
    }

    @Test(expected = IllegalArgumentException.class)
    public void addTo_stringWithStupidlyPlacedId() throws Exception {
        div(attrs("id1#id2")).render();
    }

}
