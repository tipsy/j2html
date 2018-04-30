package j2html.tags;

import org.junit.Test;

import java.util.Arrays;

import static j2html.TagCreator.div;
import static j2html.TagCreator.each;
import static j2html.TagCreator.li;
import static j2html.TagCreator.p;
import static j2html.TagCreator.pre;
import static j2html.TagCreator.textarea;
import static j2html.TagCreator.ul;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class RenderFormattedTest {

    @Test
    public void testFormattedTags() throws Exception {
        assertThat(div(p("Hello")).renderFormatted(), is("<div>\n    <p>\n        Hello\n    </p>\n</div>\n"));
    }

    @Test
    public void testFormattedTags_doesntFormatPre() throws Exception {
        assertThat(div(pre("public void renderModel(Appendable writer, Object model) throws IOException {\n" +
            "        writer.append(text);\n" +
            "    }")).renderFormatted(), is("<div>\n" +
            "    <pre>public void renderModel(Appendable writer, Object model) throws IOException {\n" +
            "        writer.append(text);\n" +
            "    }</pre>\n" +
            "</div>\n"));
    }

    @Test
    public void testFormattedTags_doesntFormatTextArea() throws Exception {
        assertThat(div(textarea("fred\ntom")).renderFormatted(), is("<div>\n" +
            "    <textarea>fred\n" +
            "tom</textarea>\n" +
            "</div>\n"));
    }

    @Test
    public void testFormattedTags_each() throws Exception {
        assertThat(ul(each(asList(1, 2, 3), i -> li("Number " + i))).renderFormatted(), is(
            "<ul>\n" +
                "    <li>\n" +
                "        Number 1\n" +
                "    </li>\n" +
                "    <li>\n" +
                "        Number 2\n" +
                "    </li>\n" +
                "    <li>\n" +
                "        Number 3\n" +
                "    </li>\n" +
                "</ul>\n"
        ));
    }

}
