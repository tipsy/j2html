package j2html.tags;

import j2html.rendering.FlatHtml;
import j2html.rendering.IndentedHtml;
import org.junit.Test;

import java.io.IOException;

import static j2html.TagCreator.rawHtml;
import static j2html.TagCreator.text;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TextTest {

    @Test
    public void null_text_is_rendered_as_a_string_literal() throws IOException {
        assertThat(text(null).render(FlatHtml.inMemory()).toString(), is("null"));
        assertThat(text(null).render(IndentedHtml.inMemory()).toString(), is("null\n"));
    }

    @Test
    public void null_unescaped_text_is_rendered_as_a_string_literal() throws IOException {
        assertThat(rawHtml(null).render(FlatHtml.inMemory()).toString(), is("null"));
        assertThat(rawHtml(null).render(IndentedHtml.inMemory()).toString(), is("null\n"));
    }
}
