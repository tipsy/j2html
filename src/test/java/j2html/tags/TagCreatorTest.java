package j2html.tags;

import org.junit.*;
import java.util.*;
import java.util.stream.*;
import static j2html.TagCreator.*;
import static org.junit.Assert.*;

public class TagCreatorTest {

    private static final String EOL = System.getProperty("line.separator"); //System independent End Of Line

    List<Employee> employees = Arrays.asList(new Employee(1, "Name 1", "Title 1"), new Employee(2, "Name 2", "Title 2"), new Employee(3, "Name 3", "Title 3"));

    @Test
    public void testEach() throws Exception {
        String j2htmlMap = ul().with(
                li("Begin list"),
                each(employees, employee ->
                        li().with(
                                h2(employee.name),
                                p(employee.title)
                        )
                )
        ).render();
        String javaMap = ul().with(
                li("Begin list"),
                unsafeHtml(employees.stream().map(employee ->
                        li().with(
                                h2(employee.name),
                                p(employee.title)
                        )
                ).map(DomContent::render).collect(Collectors.joining()))
        ).render();
        assertEquals(j2htmlMap, javaMap);
        assertEquals(j2htmlMap, "<ul><li>Begin list</li><li><h2>Name 1</h2><p>Title 1</p></li><li><h2>Name 2</h2><p>Title 2</p></li><li><h2>Name 3</h2><p>Title 3</p></li></ul>");
    }

    @Test
    public void testFilter() throws Exception {
        String j2htmlFilter = ul().with(
                li("Begin list"),
                each(filter(employees, e -> e.id % 2 == 1), e ->
                        li().with(
                                h2(e.name),
                                p(e.title)
                        )
                )
        ).render();
        String javaFilter = ul().with(
                li("Begin list"),
                unsafeHtml(employees.stream().filter(e -> e.id % 2 == 1).map(e ->
                        li().with(
                                h2(e.name),
                                p(e.title)
                        )
                ).map(DomContent::render).collect(Collectors.joining()))
        ).render();
        assertEquals(j2htmlFilter, javaFilter);
        assertEquals(j2htmlFilter, "<ul><li>Begin list</li><li><h2>Name 1</h2><p>Title 1</p></li><li><h2>Name 3</h2><p>Title 3</p></li></ul>");
    }

    @Test
    public void testAllTags() throws Exception {

        //Special Tags
        assertEquals(tag("tagname").render(), "<tagname></tagname>");
        assertEquals(emptyTag("tagname").render(), "<tagname>");
        assertEquals(text("text").render(), "text");
        assertEquals(text("<script> and \"</script>\"").render(), "&lt;script&gt; and &quot;&lt;/script&gt;&quot;");
        assertEquals(unsafeHtml("<script>").render(), "<script>");
        assertEquals(styleWithInlineFile_min("/test.css").render(), "<style>body{background:#daa520;margin-bottom:10px;margin-left:10px;margin-right:10px;margin-top:10px}</style>");
        assertEquals(scriptWithInlineFile_min("/test.js").render(), "<script>(function(){console.log(15)})();</script>");
        assertEquals(fileAsString("/test.html").render(), "<body>" + EOL + "    Any content" + EOL + "</body>" + EOL);
        assertEquals(fileAsEscapedString("/test.html").render(), "&lt;body&gt;" + EOL + "    Any content" + EOL + "&lt;/body&gt;" + EOL);
        assertEquals(fileAsString("/test.java").render(), "public class AnyContent{}" + EOL);

        //EmptyTags
        assertEquals(document().render(), "<!DOCTYPE html>");
        assertEquals(area().render(), "<area>");
        assertEquals(base().render(), "<base>");
        assertEquals(br().render(), "<br>");
        assertEquals(col().render(), "<col>");
        assertEquals(embed().render(), "<embed>");
        assertEquals(hr().render(), "<hr>");
        assertEquals(img().render(), "<img>");
        assertEquals(input().render(), "<input>");
        assertEquals(keygen().render(), "<keygen>");
        assertEquals(link().render(), "<link>");
        assertEquals(meta().render(), "<meta>");
        assertEquals(param().render(), "<param>");
        assertEquals(source().render(), "<source>");
        assertEquals(track().render(), "<track>");
        assertEquals(wbr().render(), "<wbr>");

        //ContainerTags
        assertEquals(a().render(), "<a></a>");
        assertEquals(a("Text").render(), "<a>Text</a>");
        assertEquals(abbr().render(), "<abbr></abbr>");
        assertEquals(address().render(), "<address></address>");
        assertEquals(article().render(), "<article></article>");
        assertEquals(aside().render(), "<aside></aside>");
        assertEquals(audio().render(), "<audio></audio>");
        assertEquals(b().render(), "<b></b>");
        assertEquals(b("Text").render(), "<b>Text</b>");
        assertEquals(bdi().render(), "<bdi></bdi>");
        assertEquals(bdi("Text").render(), "<bdi>Text</bdi>");
        assertEquals(bdo().render(), "<bdo></bdo>");
        assertEquals(bdo("Text").render(), "<bdo>Text</bdo>");
        assertEquals(blockquote().render(), "<blockquote></blockquote>");
        assertEquals(blockquote("Text").render(), "<blockquote>Text</blockquote>");
        assertEquals(body().render(), "<body></body>");
        assertEquals(button().render(), "<button></button>");
        assertEquals(button("Text").render(), "<button>Text</button>");
        assertEquals(canvas().render(), "<canvas></canvas>");
        assertEquals(caption().render(), "<caption></caption>");
        assertEquals(caption("Text").render(), "<caption>Text</caption>");
        assertEquals(cite().render(), "<cite></cite>");
        assertEquals(cite("Text").render(), "<cite>Text</cite>");
        assertEquals(code().render(), "<code></code>");
        assertEquals(colgroup().render(), "<colgroup></colgroup>");
        assertEquals(datalist().render(), "<datalist></datalist>");
        assertEquals(dd().render(), "<dd></dd>");
        assertEquals(dd("Text").render(), "<dd>Text</dd>");
        assertEquals(del().render(), "<del></del>");
        assertEquals(del("Text").render(), "<del>Text</del>");
        assertEquals(details().render(), "<details></details>");
        assertEquals(dfn().render(), "<dfn></dfn>");
        assertEquals(dfn("Text").render(), "<dfn>Text</dfn>");
        assertEquals(dialog().render(), "<dialog></dialog>");
        assertEquals(dialog("Text").render(), "<dialog>Text</dialog>");
        assertEquals(div().render(), "<div></div>");
        assertEquals(dl().render(), "<dl></dl>");
        assertEquals(dt().render(), "<dt></dt>");
        assertEquals(dt("Text").render(), "<dt>Text</dt>");
        assertEquals(em().render(), "<em></em>");
        assertEquals(em("Text").render(), "<em>Text</em>");
        assertEquals(fieldset().render(), "<fieldset></fieldset>");
        assertEquals(figcaption().render(), "<figcaption></figcaption>");
        assertEquals(figcaption("Text").render(), "<figcaption>Text</figcaption>");
        assertEquals(figure().render(), "<figure></figure>");
        assertEquals(footer().render(), "<footer></footer>");
        assertEquals(font().render(), "<font></font>");
        assertEquals(font("Text").render(), "<font>Text</font>");
        assertEquals(form().render(), "<form></form>");
        assertEquals(h1().render(), "<h1></h1>");
        assertEquals(h1("Text").render(), "<h1>Text</h1>");
        assertEquals(h2().render(), "<h2></h2>");
        assertEquals(h2("Text").render(), "<h2>Text</h2>");
        assertEquals(h3().render(), "<h3></h3>");
        assertEquals(h3("Text").render(), "<h3>Text</h3>");
        assertEquals(h4().render(), "<h4></h4>");
        assertEquals(h4("Text").render(), "<h4>Text</h4>");
        assertEquals(h5().render(), "<h5></h5>");
        assertEquals(h5("Text").render(), "<h5>Text</h5>");
        assertEquals(h6().render(), "<h6></h6>");
        assertEquals(h6("Text").render(), "<h6>Text</h6>");
        assertEquals(head().render(), "<head></head>");
        assertEquals(header().render(), "<header></header>");
        assertEquals(html().render(), "<html></html>");
        assertEquals(i().render(), "<i></i>");
        assertEquals(i("Text").render(), "<i>Text</i>");
        assertEquals(iframe().render(), "<iframe></iframe>");
        assertEquals(ins().render(), "<ins></ins>");
        assertEquals(ins("Text").render(), "<ins>Text</ins>");
        assertEquals(kbd().render(), "<kbd></kbd>");
        assertEquals(label().render(), "<label></label>");
        assertEquals(label("Text").render(), "<label>Text</label>");
        assertEquals(legend().render(), "<legend></legend>");
        assertEquals(legend("Text").render(), "<legend>Text</legend>");
        assertEquals(li().render(), "<li></li>");
        assertEquals(li("Text").render(), "<li>Text</li>");
        assertEquals(main().render(), "<main></main>");
        assertEquals(map().render(), "<map></map>");
        assertEquals(mark().render(), "<mark></mark>");
        assertEquals(menu().render(), "<menu></menu>");
        assertEquals(menuitem().render(), "<menuitem></menuitem>");
        assertEquals(meter().render(), "<meter></meter>");
        assertEquals(nav().render(), "<nav></nav>");
        assertEquals(noscript().render(), "<noscript></noscript>");
        assertEquals(object().render(), "<object></object>");
        assertEquals(ol().render(), "<ol></ol>");
        assertEquals(optgroup().render(), "<optgroup></optgroup>");
        assertEquals(option().render(), "<option></option>");
        assertEquals(option("Text").render(), "<option>Text</option>");
        assertEquals(output().render(), "<output></output>");
        assertEquals(p().render(), "<p></p>");
        assertEquals(p("Text").render(), "<p>Text</p>");
        assertEquals(pre().render(), "<pre></pre>");
        assertEquals(progress().render(), "<progress></progress>");
        assertEquals(q().render(), "<q></q>");
        assertEquals(q("Text").render(), "<q>Text</q>");
        assertEquals(rp().render(), "<rp></rp>");
        assertEquals(rt().render(), "<rt></rt>");
        assertEquals(ruby().render(), "<ruby></ruby>");
        assertEquals(s().render(), "<s></s>");
        assertEquals(s("Text").render(), "<s>Text</s>");
        assertEquals(samp().render(), "<samp></samp>");
        assertEquals(script().render(), "<script></script>");
        assertEquals(section().render(), "<section></section>");
        assertEquals(select().render(), "<select></select>");
        assertEquals(small().render(), "<small></small>");
        assertEquals(small("Text").render(), "<small>Text</small>");
        assertEquals(span().render(), "<span></span>");
        assertEquals(span("Text").render(), "<span>Text</span>");
        assertEquals(strong().render(), "<strong></strong>");
        assertEquals(strong("Text").render(), "<strong>Text</strong>");
        assertEquals(style().render(), "<style></style>");
        assertEquals(sub().render(), "<sub></sub>");
        assertEquals(sub("Text").render(), "<sub>Text</sub>");
        assertEquals(summary().render(), "<summary></summary>");
        assertEquals(summary("Text").render(), "<summary>Text</summary>");
        assertEquals(sup().render(), "<sup></sup>");
        assertEquals(sup("Text").render(), "<sup>Text</sup>");
        assertEquals(table().render(), "<table></table>");
        assertEquals(tbody().render(), "<tbody></tbody>");
        assertEquals(td().render(), "<td></td>");
        assertEquals(td("Text").render(), "<td>Text</td>");
        assertEquals(textarea().render(), "<textarea></textarea>");
        assertEquals(tfoot().render(), "<tfoot></tfoot>");
        assertEquals(th().render(), "<th></th>");
        assertEquals(th("Text").render(), "<th>Text</th>");
        assertEquals(thead().render(), "<thead></thead>");
        assertEquals(time().render(), "<time></time>");
        assertEquals(title().render(), "<title></title>");
        assertEquals(title("Text").render(), "<title>Text</title>");
        assertEquals(tr().render(), "<tr></tr>");
        assertEquals(u().render(), "<u></u>");
        assertEquals(u("Text").render(), "<u>Text</u>");
        assertEquals(ul().render(), "<ul></ul>");
        assertEquals(var().render(), "<var></var>");
        assertEquals(video().render(), "<video></video>");
    }

}
