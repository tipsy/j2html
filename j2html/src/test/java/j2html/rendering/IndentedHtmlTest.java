package j2html.rendering;

import j2html.Config;
import org.junit.Test;

import java.io.IOException;

import static j2html.TagCreator.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class IndentedHtmlTest {

    @Test
    public void unescaped_text_is_not_modified() throws Exception {
        assertThat(
            IndentedHtml.inMemory().appendUnescapedText("<>&\"\'").output().toString(),
            is("<>&\"\'\n")
        );
    }

    @Test
    public void escaped_text_replaces_special_characters_with_character_entities() throws Exception {
        assertThat(
            IndentedHtml.inMemory().appendEscapedText("<>&\"\'").output().toString(),
            is("&lt;&gt;&amp;&quot;&#x27;\n")
        );
    }

    @Test
    public void root_elements_are_not_indented() throws IOException {
        assertThat(
            div().render(IndentedHtml.inMemory()).toString(),
            is("<div>\n</div>\n")
        );

        assertThat(
            input().render(IndentedHtml.inMemory()).toString(),
            is("<input>\n")
        );
    }

    @Test
    public void indentation_increases_for_each_layer_of_children() throws IOException {
        assertThat(
            div(div(div(input()))).render(IndentedHtml.inMemory()).toString(),
            is(
                "<div>\n" +
                    "    <div>\n" +
                    "        <div>\n" +
                    "            <input>\n" +
                    "        </div>\n" +
                    "    </div>\n" +
                    "</div>\n"
            )
        );
    }

    @Test
    public void lines_of_text_are_indented_as_siblings() throws IOException {
        assertThat(
            div("abc\ndef\nghi").render(IndentedHtml.inMemory()).toString(),
            is(
                "<div>\n" +
                    "    abc\n" +
                    "    def\n" +
                    "    ghi\n" +
                    "</div>\n"
            )
        );
    }

    @Test
    public void indentation_remains_the_same_for_sibling() throws IOException {
        assertThat(
            div(div(), input(), text("abc\ndef"), div()).render(IndentedHtml.inMemory()).toString(),
            is(
                "<div>\n" +
                    "    <div>\n" +
                    "    </div>\n" +
                    "    <input>\n" +
                    "    abc\n" +
                    "    def\n" +
                    "    <div>\n" +
                    "    </div>\n" +
                    "</div>\n"
            )
        );
    }

    @Test
    public void attributes_are_defined_within_the_start_tag_on_a_single_line() throws IOException {
        assertThat(
            div(
                div(
                    div(
                        input().withId("D").withClass("xyz")
                    ).withId("C")
                ).withId("B")
            ).withId("A").render(IndentedHtml.inMemory()).toString(),
            is(
                "<div id=\"A\">\n" +
                    "    <div id=\"B\">\n" +
                    "        <div id=\"C\">\n" +
                    "            <input id=\"D\" class=\"xyz\">\n" +
                    "        </div>\n" +
                    "    </div>\n" +
                    "</div>\n"
            )
        );
    }

    @Test
    public void attribute_values_are_escaped() throws IOException {
        assertThat(
            div().withId("<>&\"\'").render(IndentedHtml.inMemory()).toString(),
            is("<div id=\"&lt;&gt;&amp;&quot;&#x27;\">\n</div>\n")
        );
    }

    @Test
    public void content_within_pre_elements_is_not_indented() throws IOException {
        //Single line text.
        assertThat(
            div(pre("abc")).render(IndentedHtml.inMemory()).toString(),
            is(
                "<div>\n" +
                    "    <pre>abc</pre>\n" +
                    "</div>\n"
            )
        );

        //Multiline text.
        assertThat(
            div(pre("abc\ndef")).render(IndentedHtml.inMemory()).toString(),
            is(
                "<div>\n" +
                    "    <pre>abc\ndef</pre>\n" +
                    "</div>\n"
            )
        );

        //Child elements.
        assertThat(
            div(pre(code("abc\ndef"))).render(IndentedHtml.inMemory()).toString(),
            is(
                "<div>\n" +
                    "    <pre><code>abc\ndef</code></pre>\n" +
                    "</div>\n"
            )
        );
    }

    @Test
    public void content_within_textarea_elements_is_not_indented() throws IOException {
        //Single line text.
        assertThat(
            textarea("abc").render(IndentedHtml.inMemory()).toString(),
            is("<textarea>abc</textarea>\n")
        );

        //Multiline text.
        assertThat(
            textarea("abc\ndef").render(IndentedHtml.inMemory()).toString(),
            is("<textarea>abc\ndef</textarea>\n")
        );
    }

    @Test
    public void empty_tags_are_closed_when_configured() throws IOException {
        assertThat(
            input().render(IndentedHtml.inMemory(Config.defaults().withEmptyTagsClosed(false))).toString(),
            is("<input>\n")
        );

        assertThat(
            input().render(IndentedHtml.inMemory(Config.defaults().withEmptyTagsClosed(true))).toString(),
            is("<input/>\n")
        );
    }

    @Test
    public void end_tags_are_never_closed() throws IOException {
        assertThat(
            div().render(IndentedHtml.inMemory(Config.defaults().withEmptyTagsClosed(false))).toString(),
            is("<div>\n</div>\n")
        );

        assertThat(
            div().render(IndentedHtml.inMemory(Config.defaults().withEmptyTagsClosed(true))).toString(),
            is("<div>\n</div>\n")
        );
    }
}
