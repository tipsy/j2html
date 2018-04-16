package j2html.tags;

import j2html.Config;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import static j2html.TagCreator.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

public class TagCreatorTest {

    private static final String EOL = System.getProperty("line.separator"); // System independent End Of Line

    List<Employee> employees = Arrays.asList(new Employee(1, "Name 1", "Title 1"), new Employee(2, "Name 2", "Title 2"), new Employee(3, "Name 3", "Title 3"));

    Map<Integer, Employee> employeeMap = new HashMap<>();

    @Before
    public void setUp() {
        employeeMap.put(1, new Employee(1, "Name 1", "Title 1"));
        employeeMap.put(2, new Employee(2, "Name 2", "Title 2"));
        employeeMap.put(3, new Employee(3, "Name 3", "Title 3"));
    }

    @Test
    public void testDocument() throws Exception {
        Config.closeEmptyTags = true;
        assertEquals("<!DOCTYPE html>", document().render());
        assertEquals("<!DOCTYPE html><html></html>", document(html()));
        Config.closeEmptyTags = false;
        assertEquals("<!DOCTYPE html>", document().render());
        assertEquals("<!DOCTYPE html><html></html>", document(html()));
    }

    @Test
    public void testIff() throws Exception {
        String expected = "<div><p>Test</p><a href=\"#\">Test</a></div>";
        String actual = div(
            p("Test"),
            iff(1 == 1, a("Test").withHref("#")),
            iff(1 == 2, a("Tast").withHref("#"))
        ).render();
        assertThat(actual, is(expected));
    }
    
    @Test
    public void testIffOptional() {
        String expected = "<div><p>Test</p><a href=\"#1\">Test</a></div>";
        String actual = div(
            p("Test"),
            iff(Optional.of(1), i -> a("Test").withHref("#" + i)),
            iff(Optional.empty(), i -> a("Tast").withHref("#2"))
        ).render();
        assertThat(actual, is(expected));
    }

    @Test
    public void testIffElse() throws Exception {
        String expected = "<div><p>Tast</p></div>";
        String actual = div(iffElse(1 == 2, p("Test"), p("Tast"))).render();
        assertThat(actual, is(expected));
    }

    @Test
    public void testJoin() throws Exception {
        String expected = "This is my joined string. It has a <a href=\"#\">link in the middle</a> and <code>code at the end</code>.";
        String actual = join("This is my joined string. It has a", a("link in the middle").withHref("#"), "and", code("code at the end"), ".").render();
        assertThat(actual, is(expected));
    }

    @Test
    public void testEach() throws Exception {
        String j2htmlMap = ul().with(
            li("Begin list"),
            each(employees, employee -> li(
                h2(employee.name),
                p(employee.title)
            ))
        ).render();
        String javaMap = ul().with(
            li("Begin list"),
            rawHtml(employees.stream().map(employee -> li(
                h2(employee.name),
                p(employee.title)
            )).map(DomContent::render).collect(Collectors.joining()))
        ).render();
        assertThat(j2htmlMap, is("<ul><li>Begin list</li><li><h2>Name 1</h2><p>Title 1</p></li><li><h2>Name 2</h2><p>Title 2</p></li><li><h2>Name 3</h2><p>Title 3</p></li></ul>"));
        assertThat(j2htmlMap.equals(javaMap), is(true));
    }

    @Test
    public void testEachWithMap() {
         final String j2htmlMap = ul().with(
                 li("Begin list"),
                each(employeeMap, entry -> li(entry.getKey() + "-" + entry.getValue().name))
        ).render();

        assertThat(j2htmlMap, is("<ul><li>Begin list</li><li>1-Name 1</li><li>2-Name 2</li><li>3-Name 3</li></ul>"));
    }

    @Test
    public void testFilter() throws Exception {
        String j2htmlFilter = ul().with(
            li("Begin list"),
            each(filter(employees, e -> e.id % 2 == 1), employee -> li(
                h2(employee.name),
                p(employee.title)
            ))
        ).render();
        String javaFilter = ul().with(
            li("Begin list"),
            rawHtml(employees.stream().filter(e -> e.id % 2 == 1).map(employee -> li(
                h2(employee.name),
                p(employee.title)
            )).map(DomContent::render).collect(Collectors.joining()))
        ).render();
        assertThat(j2htmlFilter, is("<ul><li>Begin list</li><li><h2>Name 1</h2><p>Title 1</p></li><li><h2>Name 3</h2><p>Title 3</p></li></ul>"));
        assertThat(j2htmlFilter.equals(javaFilter), is(true));
    }

    @Test
    public void testAllTags() throws Exception {

        //Special Tags
        assertThat(tag("tagname").render(), is("<tagname></tagname>"));
        assertThat(emptyTag("tagname").render(), is("<tagname>"));
        assertThat(text("text").render(), is("text"));
        assertThat(text("<script> and \"</script>\"").render(), is("&lt;script&gt; and &quot;&lt;/script&gt;&quot;"));
        assertThat(rawHtml("<script>").render(), is("<script>"));

        //EmptyTags
        assertThat(document().render(), is("<!DOCTYPE html>"));
        assertThat(area().render(), is("<area>"));
        assertThat(base().render(), is("<base>"));
        assertThat(br().render(), is("<br>"));
        assertThat(col().render(), is("<col>"));
        assertThat(embed().render(), is("<embed>"));
        assertThat(hr().render(), is("<hr>"));
        assertThat(img().render(), is("<img>"));
        assertThat(input().render(), is("<input>"));
        assertThat(keygen().render(), is("<keygen>"));
        assertThat(link().render(), is("<link>"));
        assertThat(meta().render(), is("<meta>"));
        assertThat(param().render(), is("<param>"));
        assertThat(source().render(), is("<source>"));
        assertThat(track().render(), is("<track>"));
        assertThat(wbr().render(), is("<wbr>"));

        //ContainerTags
        assertThat(a().render(), is("<a></a>"));
        assertThat(a("Text").render(), is("<a>Text</a>"));
        assertThat(abbr().render(), is("<abbr></abbr>"));
        assertThat(address().render(), is("<address></address>"));
        assertThat(article().render(), is("<article></article>"));
        assertThat(aside().render(), is("<aside></aside>"));
        assertThat(audio().render(), is("<audio></audio>"));
        assertThat(b().render(), is("<b></b>"));
        assertThat(b("Text").render(), is("<b>Text</b>"));
        assertThat(bdi().render(), is("<bdi></bdi>"));
        assertThat(bdi("Text").render(), is("<bdi>Text</bdi>"));
        assertThat(bdo().render(), is("<bdo></bdo>"));
        assertThat(bdo("Text").render(), is("<bdo>Text</bdo>"));
        assertThat(blockquote().render(), is("<blockquote></blockquote>"));
        assertThat(blockquote("Text").render(), is("<blockquote>Text</blockquote>"));
        assertThat(body().render(), is("<body></body>"));
        assertThat(button().render(), is("<button></button>"));
        assertThat(button("Text").render(), is("<button>Text</button>"));
        assertThat(canvas().render(), is("<canvas></canvas>"));
        assertThat(caption().render(), is("<caption></caption>"));
        assertThat(caption("Text").render(), is("<caption>Text</caption>"));
        assertThat(cite().render(), is("<cite></cite>"));
        assertThat(cite("Text").render(), is("<cite>Text</cite>"));
        assertThat(code().render(), is("<code></code>"));
        assertThat(colgroup().render(), is("<colgroup></colgroup>"));
        assertThat(datalist().render(), is("<datalist></datalist>"));
        assertThat(dd().render(), is("<dd></dd>"));
        assertThat(dd("Text").render(), is("<dd>Text</dd>"));
        assertThat(del().render(), is("<del></del>"));
        assertThat(del("Text").render(), is("<del>Text</del>"));
        assertThat(details().render(), is("<details></details>"));
        assertThat(dfn().render(), is("<dfn></dfn>"));
        assertThat(dfn("Text").render(), is("<dfn>Text</dfn>"));
        assertThat(dialog().render(), is("<dialog></dialog>"));
        assertThat(dialog("Text").render(), is("<dialog>Text</dialog>"));
        assertThat(div().render(), is("<div></div>"));
        assertThat(dl().render(), is("<dl></dl>"));
        assertThat(dt().render(), is("<dt></dt>"));
        assertThat(dt("Text").render(), is("<dt>Text</dt>"));
        assertThat(em().render(), is("<em></em>"));
        assertThat(em("Text").render(), is("<em>Text</em>"));
        assertThat(fieldset().render(), is("<fieldset></fieldset>"));
        assertThat(figcaption().render(), is("<figcaption></figcaption>"));
        assertThat(figcaption("Text").render(), is("<figcaption>Text</figcaption>"));
        assertThat(figure().render(), is("<figure></figure>"));
        assertThat(footer().render(), is("<footer></footer>"));
        assertThat(form().render(), is("<form></form>"));
        assertThat(h1().render(), is("<h1></h1>"));
        assertThat(h1("Text").render(), is("<h1>Text</h1>"));
        assertThat(h2().render(), is("<h2></h2>"));
        assertThat(h2("Text").render(), is("<h2>Text</h2>"));
        assertThat(h3().render(), is("<h3></h3>"));
        assertThat(h3("Text").render(), is("<h3>Text</h3>"));
        assertThat(h4().render(), is("<h4></h4>"));
        assertThat(h4("Text").render(), is("<h4>Text</h4>"));
        assertThat(h5().render(), is("<h5></h5>"));
        assertThat(h5("Text").render(), is("<h5>Text</h5>"));
        assertThat(h6().render(), is("<h6></h6>"));
        assertThat(h6("Text").render(), is("<h6>Text</h6>"));
        assertThat(head().render(), is("<head></head>"));
        assertThat(header().render(), is("<header></header>"));
        assertThat(html().render(), is("<html></html>"));
        assertThat(i().render(), is("<i></i>"));
        assertThat(i("Text").render(), is("<i>Text</i>"));
        assertThat(iframe().render(), is("<iframe></iframe>"));
        assertThat(ins().render(), is("<ins></ins>"));
        assertThat(ins("Text").render(), is("<ins>Text</ins>"));
        assertThat(kbd().render(), is("<kbd></kbd>"));
        assertThat(label().render(), is("<label></label>"));
        assertThat(label("Text").render(), is("<label>Text</label>"));
        assertThat(legend().render(), is("<legend></legend>"));
        assertThat(legend("Text").render(), is("<legend>Text</legend>"));
        assertThat(li().render(), is("<li></li>"));
        assertThat(li("Text").render(), is("<li>Text</li>"));
        assertThat(main().render(), is("<main></main>"));
        assertThat(map().render(), is("<map></map>"));
        assertThat(mark().render(), is("<mark></mark>"));
        assertThat(menu().render(), is("<menu></menu>"));
        assertThat(menuitem().render(), is("<menuitem></menuitem>"));
        assertThat(meter().render(), is("<meter></meter>"));
        assertThat(nav().render(), is("<nav></nav>"));
        assertThat(noscript().render(), is("<noscript></noscript>"));
        assertThat(object().render(), is("<object></object>"));
        assertThat(ol().render(), is("<ol></ol>"));
        assertThat(optgroup().render(), is("<optgroup></optgroup>"));
        assertThat(option().render(), is("<option></option>"));
        assertThat(option("Text").render(), is("<option>Text</option>"));
        assertThat(output().render(), is("<output></output>"));
        assertThat(p().render(), is("<p></p>"));
        assertThat(p("Text").render(), is("<p>Text</p>"));
        assertThat(pre().render(), is("<pre></pre>"));
        assertThat(progress().render(), is("<progress></progress>"));
        assertThat(q().render(), is("<q></q>"));
        assertThat(q("Text").render(), is("<q>Text</q>"));
        assertThat(rp().render(), is("<rp></rp>"));
        assertThat(rt().render(), is("<rt></rt>"));
        assertThat(ruby().render(), is("<ruby></ruby>"));
        assertThat(s().render(), is("<s></s>"));
        assertThat(s("Text").render(), is("<s>Text</s>"));
        assertThat(samp().render(), is("<samp></samp>"));
        assertThat(script().render(), is("<script></script>"));
        assertThat(section().render(), is("<section></section>"));
        assertThat(select().render(), is("<select></select>"));
        assertThat(small().render(), is("<small></small>"));
        assertThat(small("Text").render(), is("<small>Text</small>"));
        assertThat(span().render(), is("<span></span>"));
        assertThat(span("Text").render(), is("<span>Text</span>"));
        assertThat(strong().render(), is("<strong></strong>"));
        assertThat(strong("Text").render(), is("<strong>Text</strong>"));
        assertThat(style().render(), is("<style></style>"));
        assertThat(sub().render(), is("<sub></sub>"));
        assertThat(sub("Text").render(), is("<sub>Text</sub>"));
        assertThat(summary().render(), is("<summary></summary>"));
        assertThat(summary("Text").render(), is("<summary>Text</summary>"));
        assertThat(sup().render(), is("<sup></sup>"));
        assertThat(sup("Text").render(), is("<sup>Text</sup>"));
        assertThat(table().render(), is("<table></table>"));
        assertThat(tbody().render(), is("<tbody></tbody>"));
        assertThat(td().render(), is("<td></td>"));
        assertThat(td("Text").render(), is("<td>Text</td>"));
        assertThat(textarea().render(), is("<textarea></textarea>"));
        assertThat(tfoot().render(), is("<tfoot></tfoot>"));
        assertThat(th().render(), is("<th></th>"));
        assertThat(th("Text").render(), is("<th>Text</th>"));
        assertThat(thead().render(), is("<thead></thead>"));
        assertThat(time().render(), is("<time></time>"));
        assertThat(title().render(), is("<title></title>"));
        assertThat(title("Text").render(), is("<title>Text</title>"));
        assertThat(tr().render(), is("<tr></tr>"));
        assertThat(u().render(), is("<u></u>"));
        assertThat(u("Text").render(), is("<u>Text</u>"));
        assertThat(ul().render(), is("<ul></ul>"));
        assertThat(var().render(), is("<var></var>"));
        assertThat(video().render(), is("<video></video>"));
    }

}
