package j2html;

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

import com.carrotsearch.junitbenchmarks.BenchmarkOptions;
import com.carrotsearch.junitbenchmarks.BenchmarkRule;
import com.carrotsearch.junitbenchmarks.Clock;
import j2html.model.BrowserTitle;
import j2html.model.Button;
import j2html.model.ButtonModel;
import j2html.model.PageModel;
import j2html.model.TextTemplate;
import j2html.tags.DomContent;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

@BenchmarkOptions(
  callgc = false,
  benchmarkRounds = 50000,
  warmupRounds = 200,
  concurrency = 2,
  clock = Clock.NANO_TIME
)
public class RenderPerformanceTest {

  String expected =
      "<html><head><title>Browsertitle</title></head><body><h1>Hello World!</h1><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><p>Hello World!</p></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div><h2 id=\"title\" class=\"visible-small\">Hello World!</h2><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><p>Hello World!</p></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div><h2>Hello World!</h2><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><p>Hello World!</p></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div><h2 id=\"title\" class=\"visible-small\">Hello World!</h2><div class=\"button\"><div class=\"button-text\">Action!</div></div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><p>Hello World!</p></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div><h2>Hello World!</h2><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><p>Hello World!</p></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div><h1>Hello World!</h1><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><p>Hello World!</p></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div><h2>Hello World!</h2><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><p>Hello World!</p></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div><h2>Hello World!</h2><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><p>Hello World!</p></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div><h2>Hello World!</h2><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><p>Hello World!</p></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div><h2>Hello World!</h2><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><div><p>Hello World!</p></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></body></html>";
  @Rule public TestRule benchmarkRun = new BenchmarkRule();

  private DomContent template;

  public RenderPerformanceTest() {
    this.template =
        // @formatter:off
        html(
            head(title(new BrowserTitle())),
            body(
                h1(new TextTemplate()),
                div(
                    div(
                        div(
                            div(
                                div(
                                    div(
                                        div(
                                            div(
                                                div(
                                                    div(
                                                        div(
                                                            div(
                                                                div(
                                                                    div(
                                                                        div(
                                                                            div(
                                                                                div(
                                                                                    div(
                                                                                        div(
                                                                                            div(
                                                                                                div(
                                                                                                    div(
                                                                                                        div(
                                                                                                            div(
                                                                                                                div(
                                                                                                                    div(
                                                                                                                        p(
                                                                                                                            new TextTemplate()))))))))))))))))))))))))))),
                h2(attrs("#title.visible-small"), new TextTemplate()),
                div(
                    div(
                        div(
                            div(
                                div(
                                    div(
                                        div(
                                            div(
                                                div(
                                                    div(
                                                        div(
                                                            div(
                                                                div(
                                                                    div(
                                                                        div(
                                                                            div(
                                                                                div(
                                                                                    div(
                                                                                        div(
                                                                                            div(
                                                                                                div(
                                                                                                    div(
                                                                                                        div(
                                                                                                            div(
                                                                                                                div(
                                                                                                                    div(
                                                                                                                        p(
                                                                                                                            new TextTemplate()))))))))))))))))))))))))))),
                h2(new TextTemplate()),
                div(
                    div(
                        div(
                            div(
                                div(
                                    div(
                                        div(
                                            div(
                                                div(
                                                    div(
                                                        div(
                                                            div(
                                                                div(
                                                                    div(
                                                                        div(
                                                                            div(
                                                                                div(
                                                                                    div(
                                                                                        div(
                                                                                            div(
                                                                                                div(
                                                                                                    div(
                                                                                                        div(
                                                                                                            div(
                                                                                                                div(
                                                                                                                    div(
                                                                                                                        p(
                                                                                                                            new TextTemplate()))))))))))))))))))))))))))),
                h2(attrs("#title.visible-small"), new TextTemplate()),
                new Button(),
                div(
                    div(
                        div(
                            div(
                                div(
                                    div(
                                        div(
                                            div(
                                                div(
                                                    div(
                                                        div(
                                                            div(
                                                                div(
                                                                    div(
                                                                        div(
                                                                            div(
                                                                                div(
                                                                                    div(
                                                                                        div(
                                                                                            div(
                                                                                                div(
                                                                                                    div(
                                                                                                        div(
                                                                                                            div(
                                                                                                                div(
                                                                                                                    div(
                                                                                                                        p(
                                                                                                                            new TextTemplate()))))))))))))))))))))))))))),
                h2(new TextTemplate()),
                div(
                    div(
                        div(
                            div(
                                div(
                                    div(
                                        div(
                                            div(
                                                div(
                                                    div(
                                                        div(
                                                            div(
                                                                div(
                                                                    div(
                                                                        div(
                                                                            div(
                                                                                div(
                                                                                    div(
                                                                                        div(
                                                                                            div(
                                                                                                div(
                                                                                                    div(
                                                                                                        div(
                                                                                                            div(
                                                                                                                div(
                                                                                                                    div(
                                                                                                                        p(
                                                                                                                            new TextTemplate()))))))))))))))))))))))))))),
                h1(new TextTemplate()),
                div(
                    div(
                        div(
                            div(
                                div(
                                    div(
                                        div(
                                            div(
                                                div(
                                                    div(
                                                        div(
                                                            div(
                                                                div(
                                                                    div(
                                                                        div(
                                                                            div(
                                                                                div(
                                                                                    div(
                                                                                        div(
                                                                                            div(
                                                                                                div(
                                                                                                    div(
                                                                                                        div(
                                                                                                            div(
                                                                                                                div(
                                                                                                                    div(
                                                                                                                        p(
                                                                                                                            new TextTemplate()))))))))))))))))))))))))))),
                h2(new TextTemplate()),
                div(
                    div(
                        div(
                            div(
                                div(
                                    div(
                                        div(
                                            div(
                                                div(
                                                    div(
                                                        div(
                                                            div(
                                                                div(
                                                                    div(
                                                                        div(
                                                                            div(
                                                                                div(
                                                                                    div(
                                                                                        div(
                                                                                            div(
                                                                                                div(
                                                                                                    div(
                                                                                                        div(
                                                                                                            div(
                                                                                                                div(
                                                                                                                    div(
                                                                                                                        p(
                                                                                                                            new TextTemplate()))))))))))))))))))))))))))),
                h2(new TextTemplate()),
                div(
                    div(
                        div(
                            div(
                                div(
                                    div(
                                        div(
                                            div(
                                                div(
                                                    div(
                                                        div(
                                                            div(
                                                                div(
                                                                    div(
                                                                        div(
                                                                            div(
                                                                                div(
                                                                                    div(
                                                                                        div(
                                                                                            div(
                                                                                                div(
                                                                                                    div(
                                                                                                        div(
                                                                                                            div(
                                                                                                                div(
                                                                                                                    div(
                                                                                                                        p(
                                                                                                                            new TextTemplate()))))))))))))))))))))))))))),
                h2(new TextTemplate()),
                div(
                    div(
                        div(
                            div(
                                div(
                                    div(
                                        div(
                                            div(
                                                div(
                                                    div(
                                                        div(
                                                            div(
                                                                div(
                                                                    div(
                                                                        div(
                                                                            div(
                                                                                div(
                                                                                    div(
                                                                                        div(
                                                                                            div(
                                                                                                div(
                                                                                                    div(
                                                                                                        div(
                                                                                                            div(
                                                                                                                div(
                                                                                                                    div(
                                                                                                                        p(
                                                                                                                            new TextTemplate()))))))))))))))))))))))))))),
                h2(new TextTemplate()),
                div(
                    div(
                        div(
                            div(
                                div(
                                    div(
                                        div(
                                            div(
                                                div(
                                                    div(
                                                        div(
                                                            div(
                                                                div(
                                                                    div(
                                                                        div(
                                                                            div(
                                                                                div(
                                                                                    div(
                                                                                        div(
                                                                                            div(
                                                                                                div(
                                                                                                    div(
                                                                                                        div(
                                                                                                            div(
                                                                                                                div(
                                                                                                                    div(
                                                                                                                        p(
                                                                                                                            new TextTemplate())))))))))))))))))))))))))))));
    // @formatter:on
  }

  private DomContent getDomContent(PageModel pageModel) throws Exception {
    return
    // @formatter:off
    html(
        head(title(pageModel.getTitle())),
        body(
            h1(pageModel.getText()),
            div(
                div(
                    div(
                        div(
                            div(
                                div(
                                    div(
                                        div(
                                            div(
                                                div(
                                                    div(
                                                        div(
                                                            div(
                                                                div(
                                                                    div(
                                                                        div(
                                                                            div(
                                                                                div(
                                                                                    div(
                                                                                        div(
                                                                                            div(
                                                                                                div(
                                                                                                    div(
                                                                                                        div(
                                                                                                            div(
                                                                                                                div(
                                                                                                                    p(
                                                                                                                        pageModel
                                                                                                                            .getText()))))))))))))))))))))))))))),
            h2(attrs("#title.visible-small"), pageModel.getText()),
            div(
                div(
                    div(
                        div(
                            div(
                                div(
                                    div(
                                        div(
                                            div(
                                                div(
                                                    div(
                                                        div(
                                                            div(
                                                                div(
                                                                    div(
                                                                        div(
                                                                            div(
                                                                                div(
                                                                                    div(
                                                                                        div(
                                                                                            div(
                                                                                                div(
                                                                                                    div(
                                                                                                        div(
                                                                                                            div(
                                                                                                                div(
                                                                                                                    p(
                                                                                                                        pageModel
                                                                                                                            .getText()))))))))))))))))))))))))))),
            h2(pageModel.getText()),
            div(
                div(
                    div(
                        div(
                            div(
                                div(
                                    div(
                                        div(
                                            div(
                                                div(
                                                    div(
                                                        div(
                                                            div(
                                                                div(
                                                                    div(
                                                                        div(
                                                                            div(
                                                                                div(
                                                                                    div(
                                                                                        div(
                                                                                            div(
                                                                                                div(
                                                                                                    div(
                                                                                                        div(
                                                                                                            div(
                                                                                                                div(
                                                                                                                    p(
                                                                                                                        pageModel
                                                                                                                            .getText()))))))))))))))))))))))))))),
            h2(attrs("#title.visible-small"), pageModel.getText()),
            div()
                .withClass("button")
                .with(
                    div().withClass("button-text").withText(pageModel.getButtonModel().getText())),
            div(
                div(
                    div(
                        div(
                            div(
                                div(
                                    div(
                                        div(
                                            div(
                                                div(
                                                    div(
                                                        div(
                                                            div(
                                                                div(
                                                                    div(
                                                                        div(
                                                                            div(
                                                                                div(
                                                                                    div(
                                                                                        div(
                                                                                            div(
                                                                                                div(
                                                                                                    div(
                                                                                                        div(
                                                                                                            div(
                                                                                                                div(
                                                                                                                    p(
                                                                                                                        pageModel
                                                                                                                            .getText()))))))))))))))))))))))))))),
            h2(pageModel.getText()),
            div(
                div(
                    div(
                        div(
                            div(
                                div(
                                    div(
                                        div(
                                            div(
                                                div(
                                                    div(
                                                        div(
                                                            div(
                                                                div(
                                                                    div(
                                                                        div(
                                                                            div(
                                                                                div(
                                                                                    div(
                                                                                        div(
                                                                                            div(
                                                                                                div(
                                                                                                    div(
                                                                                                        div(
                                                                                                            div(
                                                                                                                div(
                                                                                                                    p(
                                                                                                                        pageModel
                                                                                                                            .getText()))))))))))))))))))))))))))),
            h1(pageModel.getText()),
            div(
                div(
                    div(
                        div(
                            div(
                                div(
                                    div(
                                        div(
                                            div(
                                                div(
                                                    div(
                                                        div(
                                                            div(
                                                                div(
                                                                    div(
                                                                        div(
                                                                            div(
                                                                                div(
                                                                                    div(
                                                                                        div(
                                                                                            div(
                                                                                                div(
                                                                                                    div(
                                                                                                        div(
                                                                                                            div(
                                                                                                                div(
                                                                                                                    p(
                                                                                                                        pageModel
                                                                                                                            .getText()))))))))))))))))))))))))))),
            h2(pageModel.getText()),
            div(
                div(
                    div(
                        div(
                            div(
                                div(
                                    div(
                                        div(
                                            div(
                                                div(
                                                    div(
                                                        div(
                                                            div(
                                                                div(
                                                                    div(
                                                                        div(
                                                                            div(
                                                                                div(
                                                                                    div(
                                                                                        div(
                                                                                            div(
                                                                                                div(
                                                                                                    div(
                                                                                                        div(
                                                                                                            div(
                                                                                                                div(
                                                                                                                    p(
                                                                                                                        pageModel
                                                                                                                            .getText()))))))))))))))))))))))))))),
            h2(pageModel.getText()),
            div(
                div(
                    div(
                        div(
                            div(
                                div(
                                    div(
                                        div(
                                            div(
                                                div(
                                                    div(
                                                        div(
                                                            div(
                                                                div(
                                                                    div(
                                                                        div(
                                                                            div(
                                                                                div(
                                                                                    div(
                                                                                        div(
                                                                                            div(
                                                                                                div(
                                                                                                    div(
                                                                                                        div(
                                                                                                            div(
                                                                                                                div(
                                                                                                                    p(
                                                                                                                        pageModel
                                                                                                                            .getText()))))))))))))))))))))))))))),
            h2(pageModel.getText()),
            div(
                div(
                    div(
                        div(
                            div(
                                div(
                                    div(
                                        div(
                                            div(
                                                div(
                                                    div(
                                                        div(
                                                            div(
                                                                div(
                                                                    div(
                                                                        div(
                                                                            div(
                                                                                div(
                                                                                    div(
                                                                                        div(
                                                                                            div(
                                                                                                div(
                                                                                                    div(
                                                                                                        div(
                                                                                                            div(
                                                                                                                div(
                                                                                                                    p(
                                                                                                                        pageModel
                                                                                                                            .getText()))))))))))))))))))))))))))),
            h2(pageModel.getText()),
            div(
                div(
                    div(
                        div(
                            div(
                                div(
                                    div(
                                        div(
                                            div(
                                                div(
                                                    div(
                                                        div(
                                                            div(
                                                                div(
                                                                    div(
                                                                        div(
                                                                            div(
                                                                                div(
                                                                                    div(
                                                                                        div(
                                                                                            div(
                                                                                                div(
                                                                                                    div(
                                                                                                        div(
                                                                                                            div(
                                                                                                                div(
                                                                                                                    p(
                                                                                                                        pageModel
                                                                                                                            .getText())))))))))))))))))))))))))))));

    // @formatter:on
  }

  @Test
  public void templatePerfomanceTest() throws Exception {
    PageModel pageModel = new PageModel("Browsertitle", "Hello World!", new ButtonModel("Action!"));
    StringBuilder stringBuilder = new StringBuilder();
    template.renderModel(stringBuilder, pageModel);
    assertEquals(expected, stringBuilder.toString());
  }

  @Test
  public void staticPerfomanceTest() throws Exception {
    PageModel pageModel = new PageModel("Browsertitle", "Hello World!", new ButtonModel("Action!"));
    String result = getDomContent(pageModel).render();
    assertEquals(expected, result);
  }
}
