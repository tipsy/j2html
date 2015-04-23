package j2html.test.tags;

import org.junit.Test;

import static j2html.src.tags.TagCreator.*;
import static junit.framework.Assert.assertTrue;

public class TagCreatorTest {

    @Test
    public void testAllTags() throws Exception {
        //yea, this is generated from http://www.w3schools.com/tags/

        //EmptyTags
        assertTrue(document().render().equals("<!DOCTYPE html>"));
        assertTrue(area().render().equals("<area>"));
        assertTrue(base().render().equals("<base>"));
        assertTrue(br().render().equals("<br>"));
        assertTrue(col().render().equals("<col>"));
        assertTrue(embed().render().equals("<embed>"));
        assertTrue(hr().render().equals("<hr>"));
        assertTrue(img().render().equals("<img>"));
        assertTrue(input().render().equals("<input>"));
        assertTrue(keygen().render().equals("<keygen>"));
        assertTrue(link().render().equals("<link>"));
        assertTrue(meta().render().equals("<meta>"));
        assertTrue(param().render().equals("<param>"));
        assertTrue(source().render().equals("<source>"));
        assertTrue(track().render().equals("<track>"));
        assertTrue(wbr().render().equals("<wbr>"));

        //ContainerTags
        assertTrue(a().render().equals("<a></a>"));
        assertTrue(abbr().render().equals("<abbr></abbr>"));
        assertTrue(address().render().equals("<address></address>"));
        assertTrue(article().render().equals("<article></article>"));
        assertTrue(aside().render().equals("<aside></aside>"));
        assertTrue(audio().render().equals("<audio></audio>"));
        assertTrue(b().render().equals("<b></b>"));
        assertTrue(bdi().render().equals("<bdi></bdi>"));
        assertTrue(bdo().render().equals("<bdo></bdo>"));
        assertTrue(blockquote().render().equals("<blockquote></blockquote>"));
        assertTrue(body().render().equals("<body></body>"));
        assertTrue(button().render().equals("<button></button>"));
        assertTrue(canvas().render().equals("<canvas></canvas>"));
        assertTrue(caption().render().equals("<caption></caption>"));
        assertTrue(cite().render().equals("<cite></cite>"));
        assertTrue(code().render().equals("<code></code>"));
        assertTrue(colgroup().render().equals("<colgroup></colgroup>"));
        assertTrue(datalist().render().equals("<datalist></datalist>"));
        assertTrue(dd().render().equals("<dd></dd>"));
        assertTrue(del().render().equals("<del></del>"));
        assertTrue(details().render().equals("<details></details>"));
        assertTrue(dfn().render().equals("<dfn></dfn>"));
        assertTrue(dialog().render().equals("<dialog></dialog>"));
        assertTrue(div().render().equals("<div></div>"));
        assertTrue(dl().render().equals("<dl></dl>"));
        assertTrue(dt().render().equals("<dt></dt>"));
        assertTrue(em().render().equals("<em></em>"));
        assertTrue(fieldset().render().equals("<fieldset></fieldset>"));
        assertTrue(figcaption().render().equals("<figcaption></figcaption>"));
        assertTrue(figure().render().equals("<figure></figure>"));
        assertTrue(footer().render().equals("<footer></footer>"));
        assertTrue(form().render().equals("<form></form>"));
        assertTrue(h1().render().equals("<h1></h1>"));
        assertTrue(h2().render().equals("<h2></h2>"));
        assertTrue(h3().render().equals("<h3></h3>"));
        assertTrue(h4().render().equals("<h4></h4>"));
        assertTrue(h5().render().equals("<h5></h5>"));
        assertTrue(h6().render().equals("<h6></h6>"));
        assertTrue(head().render().equals("<head></head>"));
        assertTrue(header().render().equals("<header></header>"));
        assertTrue(html().render().equals("<html></html>"));
        assertTrue(i().render().equals("<i></i>"));
        assertTrue(iframe().render().equals("<iframe></iframe>"));
        assertTrue(ins().render().equals("<ins></ins>"));
        assertTrue(kbd().render().equals("<kbd></kbd>"));
        assertTrue(label().render().equals("<label></label>"));
        assertTrue(legend().render().equals("<legend></legend>"));
        assertTrue(li().render().equals("<li></li>"));
        assertTrue(main().render().equals("<main></main>"));
        assertTrue(map().render().equals("<map></map>"));
        assertTrue(mark().render().equals("<mark></mark>"));
        assertTrue(menu().render().equals("<menu></menu>"));
        assertTrue(menuitem().render().equals("<menuitem></menuitem>"));
        assertTrue(meter().render().equals("<meter></meter>"));
        assertTrue(nav().render().equals("<nav></nav>"));
        assertTrue(noscript().render().equals("<noscript></noscript>"));
        assertTrue(object().render().equals("<object></object>"));
        assertTrue(ol().render().equals("<ol></ol>"));
        assertTrue(optgroup().render().equals("<optgroup></optgroup>"));
        assertTrue(option().render().equals("<option></option>"));
        assertTrue(output().render().equals("<output></output>"));
        assertTrue(p().render().equals("<p></p>"));
        assertTrue(pre().render().equals("<pre></pre>"));
        assertTrue(progress().render().equals("<progress></progress>"));
        assertTrue(q().render().equals("<q></q>"));
        assertTrue(rp().render().equals("<rp></rp>"));
        assertTrue(rt().render().equals("<rt></rt>"));
        assertTrue(ruby().render().equals("<ruby></ruby>"));
        assertTrue(s().render().equals("<s></s>"));
        assertTrue(samp().render().equals("<samp></samp>"));
        assertTrue(script().render().equals("<script></script>"));
        assertTrue(section().render().equals("<section></section>"));
        assertTrue(select().render().equals("<select></select>"));
        assertTrue(small().render().equals("<small></small>"));
        assertTrue(span().render().equals("<span></span>"));
        assertTrue(strong().render().equals("<strong></strong>"));
        assertTrue(style().render().equals("<style></style>"));
        assertTrue(sub().render().equals("<sub></sub>"));
        assertTrue(summary().render().equals("<summary></summary>"));
        assertTrue(sup().render().equals("<sup></sup>"));
        assertTrue(table().render().equals("<table></table>"));
        assertTrue(tbody().render().equals("<tbody></tbody>"));
        assertTrue(td().render().equals("<td></td>"));
        assertTrue(textarea().render().equals("<textarea></textarea>"));
        assertTrue(tfoot().render().equals("<tfoot></tfoot>"));
        assertTrue(th().render().equals("<th></th>"));
        assertTrue(thead().render().equals("<thead></thead>"));
        assertTrue(time().render().equals("<time></time>"));
        assertTrue(title().render().equals("<title></title>"));
        assertTrue(tr().render().equals("<tr></tr>"));
        assertTrue(u().render().equals("<u></u>"));
        assertTrue(ul().render().equals("<ul></ul>"));
        assertTrue(var().render().equals("<var></var>"));
        assertTrue(video().render().equals("<video></video>"));
    }

}