package j2html.tags;

import org.junit.Test;

import static j2html.TagCreator.body;
import static j2html.TagCreator.br;
import static j2html.TagCreator.div;
import static j2html.TagCreator.each;
import static j2html.TagCreator.hr;
import static j2html.TagCreator.li;
import static j2html.TagCreator.p;
import static j2html.TagCreator.pre;
import static j2html.TagCreator.rawHtml;
import static j2html.TagCreator.span;
import static j2html.TagCreator.textarea;
import static j2html.TagCreator.ul;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class RenderFormattedTest {

    @Test
    public void testFormattedTags() {
        assertThat(div(p("Hello")).renderFormatted(), is("<div>\n    <p>\n        Hello\n    </p>\n</div>\n"));
    }

    @Test
    public void testFormattedTags_doesntFormatPre() {
        assertThat(div(pre("public void renderModel(Appendable writer, Object model) throws IOException {\n" +
            "        writer.append(text);\n" +
            "    }")).renderFormatted(), is("<div>\n" +
            "    <pre>public void renderModel(Appendable writer, Object model) throws IOException {\n" +
            "        writer.append(text);\n" +
            "    }</pre>\n" +
            "</div>\n"));
    }

    @Test
    public void testFormattedTags_doesntFormatPreChildren() {
        assertThat(div(pre("public void renderModel(Appendable writer, Object model) throws IOException {\n" +
            "        writer.append(text);\n" +
            "    }").with(span("Test"))).renderFormatted(), is("<div>\n" +
            "    <pre>public void renderModel(Appendable writer, Object model) throws IOException {\n" +
            "        writer.append(text);\n" +
            "    }<span>Test</span></pre>\n" +
            "</div>\n"));
    }

    @Test
    public void testFormattedTags_doesntFormatTextArea() {
        assertThat(div(textarea("fred\ntom")).renderFormatted(), is("<div>\n" +
            "    <textarea>fred\n" +
            "tom</textarea>\n" +
            "</div>\n"));
    }

    @Test
    public void testFormattedTags_each() {
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

    @Test
    public void testFormattedTags_nestedEach() {
        assertThat(div(ul(each(asList(1, 2, 3), i -> li("Number " + i)))).renderFormatted(), is(
            "<div>\n" +
                "    <ul>\n" +
                "        <li>\n" +
                "            Number 1\n" +
                "        </li>\n" +
                "        <li>\n" +
                "            Number 2\n" +
                "        </li>\n" +
                "        <li>\n" +
                "            Number 3\n" +
                "        </li>\n" +
                "    </ul>\n" +
                "</div>\n"
        ));
    }

    @Test
    public void shouldIndentEmptyTags() {
        assertThat(body(div(span("line 1"), br(), rawHtml("line 2"), hr())).renderFormatted(), is(
            "<body>\n" +
                "    <div>\n" +
                "        <span>\n" +
                "            line 1\n" +
                "        </span>\n" +
                "        <br>\n" +
                "        line 2\n" +
                "        <hr>\n" +
                "    </div>\n" +
                "</body>\n"
        ));
    }
}
