package j2html;

import j2html.tags.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class TagCreator {

    /**
     * Creates a DomContent object containing HTML using a mapping function on a collection
     * Intended usage: each(numbers, n -> li(n.toString()))
     *
     * @param collection the collection to iterate over, ex: a list of values "1, 2, 3"
     * @param mapper     the mapping function, ex: "n -> li(n.toString())"
     * @return unsafeHtml containing mapped data (ex. docs: <li>1</li><li>2</li><li>3</li>)
     */
    public static <T> DomContent each(Collection<T> collection, Function<? super T, DomContent> mapper) {
        return unsafeHtml(collection.stream().map(mapper.andThen(DomContent::render)).collect(Collectors.joining()));
    }

    /**
     * Filters a collection to a list, to be used with {@link j2html.TagCreator#each}
     * Intended usage: each(filter(numbers, n -> n % 2 == 0), n -> li(n.toString()))
     *
     * @param collection the collection to filter, ex: a list of values "1, 2, 3"
     * @param filter     the filter predicate, ex: "n -> n % 2 == 0"
     * @return the filtered collection as a list (ex. docs: 2)
     */
    public static <T> List<T> filter(Collection<T> collection, Predicate<? super T> filter) {
        return collection.stream().filter(filter).collect(Collectors.toList());
    }

    //Special tags
    public static ContainerTag tag(String tagName)          { return new ContainerTag(tagName); }
    public static EmptyTag emptyTag(String tagName)         { return new EmptyTag(tagName); }

    public static Text text(String text)                    { return new Text(text); }
    public static UnescapedText unsafeHtml(String html)     { return new UnescapedText(html); }

    public static Text fileAsEscapedString(String path)     { return text(InlineStaticResource.getFileAsString(path)); }
    public static UnescapedText fileAsString(String path)   { return unsafeHtml(InlineStaticResource.getFileAsString(path)); }

    public static ContainerTag styleWithInlineFile(String path)      { return InlineStaticResource.get(path, InlineStaticResource.TargetFormat.CSS); }
    public static ContainerTag scriptWithInlineFile(String path)     { return InlineStaticResource.get(path, InlineStaticResource.TargetFormat.JS); }
    public static ContainerTag styleWithInlineFile_min(String path)  { return InlineStaticResource.get(path, InlineStaticResource.TargetFormat.CSS_MIN); }
    public static ContainerTag scriptWithInlineFile_min(String path) { return InlineStaticResource.get(path, InlineStaticResource.TargetFormat.JS_MIN); }

    //EmptyTags
    public static EmptyTag area()                           { return new EmptyTag("area"); }
    public static EmptyTag base()                           { return new EmptyTag("base"); }
    public static EmptyTag br()                             { return new EmptyTag("br"); }
    public static EmptyTag col()                            { return new EmptyTag("col"); }
    public static EmptyTag document()                       { return new EmptyTag("!DOCTYPE html"); }
    public static EmptyTag embed()                          { return new EmptyTag("embed"); }
    public static EmptyTag hr()                             { return new EmptyTag("hr"); }
    public static EmptyTag img()                            { return new EmptyTag("img"); }
    public static EmptyTag input()                          { return new EmptyTag("input"); }
    public static EmptyTag keygen()                         { return new EmptyTag("keygen"); }
    public static EmptyTag link()                           { return new EmptyTag("link"); }
    public static EmptyTag meta()                           { return new EmptyTag("meta"); }
    public static EmptyTag param()                          { return new EmptyTag("param"); }
    public static EmptyTag source()                         { return new EmptyTag("source"); }
    public static EmptyTag track()                          { return new EmptyTag("track"); }
    public static EmptyTag wbr()                            { return new EmptyTag("wbr"); }


    //ContainerTags
    public static ContainerTag a()                          { return new ContainerTag("a"); }
    public static ContainerTag a(String text)               { return new ContainerTag("a").withText(text); }
    public static ContainerTag abbr()                       { return new ContainerTag("abbr"); }
    public static ContainerTag address()                    { return new ContainerTag("address"); }
    public static ContainerTag article()                    { return new ContainerTag("article"); }
    public static ContainerTag aside()                      { return new ContainerTag("aside"); }
    public static ContainerTag audio()                      { return new ContainerTag("audio"); }
    public static ContainerTag b()                          { return new ContainerTag("b"); }
    public static ContainerTag b(String text)               { return new ContainerTag("b").withText(text); }
    public static ContainerTag bdi()                        { return new ContainerTag("bdi"); }
    public static ContainerTag bdi(String text)             { return new ContainerTag("bdi").withText(text); }
    public static ContainerTag bdo()                        { return new ContainerTag("bdo"); }
    public static ContainerTag bdo(String text)             { return new ContainerTag("bdo").withText(text); }
    public static ContainerTag blockquote()                 { return new ContainerTag("blockquote"); }
    public static ContainerTag blockquote(String text)      { return new ContainerTag("blockquote").withText(text); }
    public static ContainerTag body()                       { return new ContainerTag("body"); }
    public static ContainerTag button()                     { return new ContainerTag("button"); }
    public static ContainerTag button(String text)          { return new ContainerTag("button").withText(text); }
    public static ContainerTag canvas()                     { return new ContainerTag("canvas"); }
    public static ContainerTag caption()                    { return new ContainerTag("caption"); }
    public static ContainerTag caption(String text)         { return new ContainerTag("caption").withText(text); }
    public static ContainerTag cite()                       { return new ContainerTag("cite"); }
    public static ContainerTag cite(String text)            { return new ContainerTag("cite").withText(text); }
    public static ContainerTag code()                       { return new ContainerTag("code"); }
    public static ContainerTag colgroup()                   { return new ContainerTag("colgroup"); }
    public static ContainerTag datalist()                   { return new ContainerTag("datalist"); }
    public static ContainerTag dd()                         { return new ContainerTag("dd"); }
    public static ContainerTag dd(String text)              { return new ContainerTag("dd").withText(text); }
    public static ContainerTag del()                        { return new ContainerTag("del"); }
    public static ContainerTag del(String text)             { return new ContainerTag("del").withText(text); }
    public static ContainerTag details()                    { return new ContainerTag("details"); }
    public static ContainerTag dfn()                        { return new ContainerTag("dfn"); }
    public static ContainerTag dfn(String text)             { return new ContainerTag("dfn").withText(text); }
    public static ContainerTag dialog()                     { return new ContainerTag("dialog"); }
    public static ContainerTag dialog(String text)          { return new ContainerTag("dialog").withText(text); }
    public static ContainerTag div()                        { return new ContainerTag("div"); }
    public static ContainerTag dl()                         { return new ContainerTag("dl"); }
    public static ContainerTag dt()                         { return new ContainerTag("dt"); }
    public static ContainerTag dt(String text)              { return new ContainerTag("dt").withText(text); }
    public static ContainerTag em()                         { return new ContainerTag("em"); }
    public static ContainerTag em(String text)              { return new ContainerTag("em").withText(text); }
    public static ContainerTag fieldset()                   { return new ContainerTag("fieldset"); }
    public static ContainerTag figcaption()                 { return new ContainerTag("figcaption"); }
    public static ContainerTag figcaption(String text)      { return new ContainerTag("figcaption").withText(text); }
    public static ContainerTag figure()                     { return new ContainerTag("figure"); }
    public static ContainerTag footer()                     { return new ContainerTag("footer"); }
    public static ContainerTag form()                       { return new ContainerTag("form"); }
    public static ContainerTag h1()                         { return new ContainerTag("h1"); }
    public static ContainerTag h1(String text)              { return new ContainerTag("h1").withText(text); }
    public static ContainerTag h2()                         { return new ContainerTag("h2"); }
    public static ContainerTag h2(String text)              { return new ContainerTag("h2").withText(text); }
    public static ContainerTag h3()                         { return new ContainerTag("h3"); }
    public static ContainerTag h3(String text)              { return new ContainerTag("h3").withText(text); }
    public static ContainerTag h4()                         { return new ContainerTag("h4"); }
    public static ContainerTag h4(String text)              { return new ContainerTag("h4").withText(text); }
    public static ContainerTag h5()                         { return new ContainerTag("h5"); }
    public static ContainerTag h5(String text)              { return new ContainerTag("h5").withText(text); }
    public static ContainerTag h6()                         { return new ContainerTag("h6"); }
    public static ContainerTag h6(String text)              { return new ContainerTag("h6").withText(text); }
    public static ContainerTag head()                       { return new ContainerTag("head"); }
    public static ContainerTag header()                     { return new ContainerTag("header"); }
    public static ContainerTag html()                       { return new ContainerTag("html"); }
    public static ContainerTag i()                          { return new ContainerTag("i"); }
    public static ContainerTag i(String text)               { return new ContainerTag("i").withText(text); }
    public static ContainerTag iframe()                     { return new ContainerTag("iframe"); }
    public static ContainerTag ins()                        { return new ContainerTag("ins"); }
    public static ContainerTag ins(String text)             { return new ContainerTag("ins").withText(text); }
    public static ContainerTag kbd()                        { return new ContainerTag("kbd"); }
    public static ContainerTag label()                      { return new ContainerTag("label"); }
    public static ContainerTag label(String text)           { return new ContainerTag("label").withText(text); }
    public static ContainerTag legend()                     { return new ContainerTag("legend"); }
    public static ContainerTag legend(String text)          { return new ContainerTag("legend").withText(text); }
    public static ContainerTag li()                         { return new ContainerTag("li"); }
    public static ContainerTag li(String text)              { return new ContainerTag("li").withText(text); }
    public static ContainerTag main()                       { return new ContainerTag("main"); }
    public static ContainerTag map()                        { return new ContainerTag("map"); }
    public static ContainerTag mark()                       { return new ContainerTag("mark"); }
    public static ContainerTag menu()                       { return new ContainerTag("menu"); }
    public static ContainerTag menuitem()                   { return new ContainerTag("menuitem"); }
    public static ContainerTag meter()                      { return new ContainerTag("meter"); }
    public static ContainerTag nav()                        { return new ContainerTag("nav"); }
    public static ContainerTag noscript()                   { return new ContainerTag("noscript"); }
    public static ContainerTag object()                     { return new ContainerTag("object"); }
    public static ContainerTag ol()                         { return new ContainerTag("ol"); }
    public static ContainerTag optgroup()                   { return new ContainerTag("optgroup"); }
    public static ContainerTag option()                     { return new ContainerTag("option"); }
    public static ContainerTag option(String text)          { return new ContainerTag("option").withText(text); }
    public static ContainerTag output()                     { return new ContainerTag("output"); }
    public static ContainerTag p()                          { return new ContainerTag("p"); }
    public static ContainerTag p(String text)               { return new ContainerTag("p").withText(text); }
    public static ContainerTag pre()                        { return new ContainerTag("pre"); }
    public static ContainerTag progress()                   { return new ContainerTag("progress"); }
    public static ContainerTag q()                          { return new ContainerTag("q"); }
    public static ContainerTag q(String text)               { return new ContainerTag("q").withText(text); }
    public static ContainerTag rp()                         { return new ContainerTag("rp"); }
    public static ContainerTag rt()                         { return new ContainerTag("rt"); }
    public static ContainerTag ruby()                       { return new ContainerTag("ruby"); }
    public static ContainerTag s()                          { return new ContainerTag("s"); }
    public static ContainerTag s(String text)               { return new ContainerTag("s").withText(text); }
    public static ContainerTag samp()                       { return new ContainerTag("samp"); }
    public static ContainerTag script()                     { return new ContainerTag("script"); }
    public static ContainerTag section()                    { return new ContainerTag("section"); }
    public static ContainerTag select()                     { return new ContainerTag("select"); }
    public static ContainerTag small()                      { return new ContainerTag("small"); }
    public static ContainerTag small(String text)           { return new ContainerTag("small").withText(text); }
    public static ContainerTag span()                       { return new ContainerTag("span"); }
    public static ContainerTag span(String text)            { return new ContainerTag("span").withText(text); }
    public static ContainerTag strong()                     { return new ContainerTag("strong"); }
    public static ContainerTag strong(String text)          { return new ContainerTag("strong").withText(text); }
    public static ContainerTag style()                      { return new ContainerTag("style"); }
    public static ContainerTag sub()                        { return new ContainerTag("sub"); }
    public static ContainerTag sub(String text)             { return new ContainerTag("sub").withText(text); }
    public static ContainerTag summary()                    { return new ContainerTag("summary"); }
    public static ContainerTag summary(String text)         { return new ContainerTag("summary").withText(text); }
    public static ContainerTag sup()                        { return new ContainerTag("sup"); }
    public static ContainerTag sup(String text)             { return new ContainerTag("sup").withText(text); }
    public static ContainerTag table()                      { return new ContainerTag("table"); }
    public static ContainerTag tbody()                      { return new ContainerTag("tbody"); }
    public static ContainerTag td()                         { return new ContainerTag("td"); }
    public static ContainerTag td(String text)              { return new ContainerTag("td").withText(text); }
    public static ContainerTag textarea()                   { return new ContainerTag("textarea"); }
    public static ContainerTag tfoot()                      { return new ContainerTag("tfoot"); }
    public static ContainerTag th()                         { return new ContainerTag("th"); }
    public static ContainerTag th(String text)              { return new ContainerTag("th").withText(text); }
    public static ContainerTag thead()                      { return new ContainerTag("thead"); }
    public static ContainerTag time()                       { return new ContainerTag("time"); }
    public static ContainerTag title()                      { return new ContainerTag("title"); }
    public static ContainerTag title(String text)           { return new ContainerTag("title").withText(text); }
    public static ContainerTag tr()                         { return new ContainerTag("tr"); }
    public static ContainerTag u()                          { return new ContainerTag("u"); }
    public static ContainerTag u(String text)               { return new ContainerTag("u").withText(text); }
    public static ContainerTag ul()                         { return new ContainerTag("ul"); }
    public static ContainerTag var()                        { return new ContainerTag("var"); }
    public static ContainerTag video()                      { return new ContainerTag("video"); }

}
