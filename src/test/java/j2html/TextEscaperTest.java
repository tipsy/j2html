package j2html;

import org.junit.Test;

import j2html.utils.SimpleEscaper;
import j2html.utils.TextEscaper;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class TextEscaperTest {

    @Test
    public void testTextEscaper() throws Exception {
        assertThat("default text escaper is SimpleEscaper",
                   Config.textEscaper, is(instanceOf(SimpleEscaper.class)));

        String expected = "&lt;div&gt;&lt;/div&gt;";
        assertThat("default text escaper works",
                   Config.textEscaper.escape("<div></div>"), is(expected));

        Config.textEscaper = new NoOpEscaper();
        assertThat("user can change text escaper implementation",
                   Config.textEscaper, is(instanceOf(NoOpEscaper.class)));

        expected = "<div></div>";
        assertThat("user provided text escaper actually works",
                   Config.textEscaper.escape("<div></div>"), is(expected));
        Config.textEscaper = new SimpleEscaper(); // reset escaper
    }

    private static class NoOpEscaper implements TextEscaper {

        @Override
        public String escape(String text) {
            return text;
        }

    }

}
