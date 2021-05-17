package j2html.rendering;

import j2html.Config;
import org.junit.Test;

import java.io.IOException;

import static j2html.TagCreator.div;
import static j2html.TagCreator.input;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class FlatHtmlTest {

    @Test
    public void start_tags_contain_attributes() throws IOException {
        assertThat(
            FlatHtml.inMemory().appendStartTag("abc")
                .appendAttribute("x", "X")
                .appendBooleanAttribute("y")
            .completeTag().output().toString(),
            is("<abc x=\"X\" y>")
        );
    }

    @Test
    public void empty_tags_contain_attributes() throws IOException {
        assertThat(
            FlatHtml.inMemory().appendEmptyTag("abc")
                .appendAttribute("x", "X")
                .appendBooleanAttribute("y")
                .completeTag().output().toString(),
            is("<abc x=\"X\" y>")
        );
    }

    @Test
    public void unescaped_text_is_not_modified() throws Exception {
        assertThat(
            FlatHtml.inMemory().appendUnescapedText("<>&\"\'").output().toString(),
            is("<>&\"\'")
        );
    }

    @Test
    public void escaped_text_replaces_special_characters_with_character_entities() throws Exception {
        assertThat(
            FlatHtml.inMemory().appendEscapedText("<>&\"\'").output().toString(),
            is("&lt;&gt;&amp;&quot;&#x27;")
        );
    }

    @Test
    public void attribute_values_are_escaped() throws IOException {
        assertThat(
            div().withId("<>&\"\'").render(FlatHtml.inMemory()).toString(),
            is("<div id=\"&lt;&gt;&amp;&quot;&#x27;\"></div>")
        );
    }

    @Test
    public void empty_tags_are_closed_when_configured() throws IOException {
        assertThat(
            input().render(FlatHtml.inMemory(Config.defaults().withEmptyTagsClosed(false))).toString(),
            is("<input>")
        );

        assertThat(
            input().render(FlatHtml.inMemory(Config.defaults().withEmptyTagsClosed(true))).toString(),
            is("<input/>")
        );
    }

    @Test
    public void end_tags_are_never_closed() throws IOException {
        assertThat(
            div().render(FlatHtml.inMemory(Config.defaults().withEmptyTagsClosed(false))).toString(),
            is("<div></div>")
        );

        assertThat(
            div().render(FlatHtml.inMemory(Config.defaults().withEmptyTagsClosed(true))).toString(),
            is("<div></div>")
        );
    }
}
