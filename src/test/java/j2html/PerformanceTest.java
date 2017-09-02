package j2html;

import java.util.concurrent.Callable;

import org.apache.commons.lang3.StringEscapeUtils;
import org.junit.Test;

import j2html.utils.EscapeUtil;

public class PerformanceTest {

    private String shortTestString = "<body>\n"
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
    public void test_escaper_performnce() throws Exception {
        timeEscaper("SimpleEscaper#short", () -> EscapeUtil.escape(shortTestString));
        timeEscaper("SimpleEscaper#long", () -> EscapeUtil.escape(longTestString));
        timeEscaper("ApacheEscaper#short", () -> StringEscapeUtils.escapeHtml4(shortTestString));
        timeEscaper("ApacheEscaper#long", () -> StringEscapeUtils.escapeHtml4(longTestString));
    }

    private void timeEscaper(String name, Callable escaper) throws Exception {
        long startTime = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            escaper.call();
        }
        long endTime = System.nanoTime();
        long durationMs = (endTime - startTime) / 1000000;
        System.out.println(String.format("%-21s%s", name + ":", +durationMs + "ms"));
    }

}
