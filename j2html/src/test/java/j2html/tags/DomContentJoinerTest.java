package j2html.tags;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class DomContentJoinerTest {
    @Test
    public void testJoin() {
        assertThat(DomContentJoiner.join(",", true, "a", "b", "c"), is(new UnescapedText("a,b,c")));
        assertThat(DomContentJoiner.join(",", false, "a", "b", "c"), is(new UnescapedText("a,b,c")));
    }
}
