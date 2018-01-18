package j2html;

import com.carrotsearch.junitbenchmarks.BenchmarkOptions;
import com.carrotsearch.junitbenchmarks.BenchmarkRule;
import com.carrotsearch.junitbenchmarks.Clock;
import j2html.utils.EscapeUtil;
import org.apache.commons.lang3.StringEscapeUtils;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@BenchmarkOptions(
  callgc = false,
  benchmarkRounds = 10000,
  warmupRounds = 200,
  concurrency = 2,
  clock = Clock.NANO_TIME
)
public class PerformanceTest {

  @Rule public TestRule benchmarkRun = new BenchmarkRule();

  private String shortTestString =
      "<body>\n"
          + "    <h1 class=\"example\">Heading!</h1>\n"
          + "    <img src=\"img/hello.png\">\n"
          + "</body>";

  // syntax-highlighted getting started example from j2html.com:
  private String longTestString =
      "<pre class=\" language-java\"><code class=\" language-java\"><span class=\"token keyword\">import</span> <span class=\"token keyword\">static</span> j2html<span class=\"token punctuation\">.</span>TagCreator<span class=\"token punctuation\">.</span>*<span class=\"token punctuation\">;</span>\n"
          + "\n"
          + "<span class=\"token keyword\">public</span> <span class=\"token keyword\">class</span> <span class=\"token class-name\">Main</span> <span class=\"token punctuation\">{</span>\n"
          + "    <span class=\"token keyword\">public</span> <span class=\"token keyword\">static</span> <span class=\"token keyword\">void</span> <span class=\"token function\">main<span class=\"token punctuation\">(</span></span>String<span class=\"token punctuation\">[</span><span class=\"token punctuation\">]</span> args<span class=\"token punctuation\">)</span> <span class=\"token punctuation\">{</span>\n"
          + "        <span class=\"token function\">body<span class=\"token punctuation\">(</span></span><span class=\"token punctuation\">)</span><span class=\"token punctuation\">.</span><span class=\"token function\">with<span class=\"token punctuation\">(</span></span>\n"
          + "                <span class=\"token function\">h1<span class=\"token punctuation\">(</span></span><span class=\"token string\">\"Heading!\"</span><span class=\"token punctuation\">)</span><span class=\"token punctuation\">.</span><span class=\"token function\">withClass<span class=\"token punctuation\">(</span></span><span class=\"token string\">\"example\"</span><span class=\"token punctuation\">)</span><span class=\"token punctuation\">,</span>\n"
          + "                <span class=\"token function\">img<span class=\"token punctuation\">(</span></span><span class=\"token punctuation\">)</span><span class=\"token punctuation\">.</span><span class=\"token function\">withSrc<span class=\"token punctuation\">(</span></span><span class=\"token string\">\"img/hello.png\"</span><span class=\"token punctuation\">)</span>\n"
          + "        <span class=\"token punctuation\">)</span><span class=\"token punctuation\">.</span><span class=\"token function\">render<span class=\"token punctuation\">(</span></span><span class=\"token punctuation\">)</span><span class=\"token punctuation\">;</span>\n"
          + "    <span class=\"token punctuation\">}</span>\n"
          + "<span class=\"token punctuation\">}</span>\n"
          + "</code></pre>";

  @Test
  public void testSimpleEscaperShort() throws Exception {
    EscapeUtil.escape(shortTestString);
  }

  @Test
  public void testSimpleEscaperLong() throws Exception {
    EscapeUtil.escape(longTestString);
  }

  @Test
  public void testApacheEscaperShort() throws Exception {
    StringEscapeUtils.escapeHtml4(shortTestString);
  }

  @Test
  public void testApacheEscaperLong() throws Exception {
    StringEscapeUtils.escapeHtml4(longTestString);
  }
}
