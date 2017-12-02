package j2html;

import com.carrotsearch.junitbenchmarks.BenchmarkOptions;
import com.carrotsearch.junitbenchmarks.BenchmarkRule;
import com.carrotsearch.junitbenchmarks.Clock;
import j2html.tags.DomContent;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import static j2html.TagCreator.attrs;
import static j2html.TagCreator.body;
import static j2html.TagCreator.div;
import static j2html.TagCreator.h1;
import static j2html.TagCreator.h2;
import static j2html.TagCreator.head;
import static j2html.TagCreator.html;
import static j2html.TagCreator.p;
import static j2html.TagCreator.title;
import static org.junit.Assert.assertEquals;

@BenchmarkOptions(callgc = false, benchmarkRounds = 50000, warmupRounds = 200, concurrency = 2, clock = Clock.NANO_TIME)
public class RenderPerformanceTest {
    String expected = "<html><head><title>Browsertitle</title></head><body><h1>Hello World!</h1><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><p>Hello World!</p></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div><h2 id=\"title\" class=\"visible-small\">Hello World!</h2><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><p>Hello World!</p></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div><h2>Hello World!</h2><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><p>Hello World!</p></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div><h2 id=\"title\" class=\"visible-small\">Hello World!</h2><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><p>Hello World!</p></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div><h2>Hello World!</h2><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><p>Hello World!</p></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div><h1>Hello World!</h1><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><p>Hello World!</p></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div><h2>Hello World!</h2><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><p>Hello World!</p></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div><h2>Hello World!</h2><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><p>Hello World!</p></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div><h2>Hello World!</h2><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><p>Hello World!</p></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div><h2>Hello World!</h2><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><p>Hello World!</p></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></body></html>";
    @Rule
    public TestRule benchmarkRun = new BenchmarkRule();

    private DomContent getDomContent(PageModel pageModel) throws Exception {
        return
            // @formatter:off
            html(
                head(
                    title(pageModel.getTitle())
                ),
                body(
                    h1(pageModel.getText()),
                    div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(p(pageModel.getText()))))))))))))))))))))))))))),
                    h2(attrs("#title.visible-small"), pageModel.getText()),
                    div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(p(pageModel.getText()))))))))))))))))))))))))))),
                    h2(pageModel.getText()),
                    div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(p(pageModel.getText()))))))))))))))))))))))))))),
                    h2(attrs("#title.visible-small"), pageModel.getText()),
                    div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(p(pageModel.getText()))))))))))))))))))))))))))),
                    h2(pageModel.getText()),
                    div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(p(pageModel.getText()))))))))))))))))))))))))))),
                    h1(pageModel.getText()),
                    div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(p(pageModel.getText()))))))))))))))))))))))))))),
                    h2(pageModel.getText()),
                    div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(p(pageModel.getText()))))))))))))))))))))))))))),
                    h2(pageModel.getText()),
                    div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(p(pageModel.getText()))))))))))))))))))))))))))),
                    h2(pageModel.getText()),
                    div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(p(pageModel.getText()))))))))))))))))))))))))))),
                    h2(pageModel.getText()),
                    div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(div(p(pageModel.getText())))))))))))))))))))))))))))
                )
            );
        // @formatter:on
    }

    @Test
    public void staticPerfomanceTest() throws Exception {
        PageModel pageModel = new PageModel("Browsertitle", "Hello World!");
        String result = getDomContent(pageModel).render();
        assertEquals(expected, result);
    }
}

class PageModel {
    String title;
    String text;

    public PageModel(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
