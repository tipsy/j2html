package j2html;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import j2html.tags.ContainerTag;
import j2html.tags.DomContent;
import j2html.tags.DomContentJoiner;
import j2html.tags.EmptyTag;
import j2html.tags.InlineStaticResource;
import j2html.tags.Text;
import j2html.tags.UnescapedText;

public class TagCreator {
    
    private TagCreator() {}

    /**
     * Generic if-expression to do if'ing inside method calls
     *
     * @param <T>       The derived generic parameter type
     * @param condition the condition to if-on
     * @param ifValue   the value to return if condition is true
     * @return value if condition is true, null otherwise
     */
    public static <T> T iff(boolean condition, T ifValue) {
        return condition ? ifValue : null;
    }

    /**
     * Like {@link j2html.TagCreator#iff}, but returns else-value instead of null
     */
    public static <T> T iffElse(boolean condition, T ifValue, T elseValue) {
        return condition ? ifValue : elseValue;
    }


    /**
     * Returns the HTML created by concatenating the input elements,
     * separated by space, in encounter order.
     * Also removes spaces before periods and commas.
     *
     * @param stringOrDomObjects the elements to join
     * @return joined elements as HTML
     */
    public static UnescapedText join(Object... stringOrDomObjects) {
        return DomContentJoiner.join(" ", true, stringOrDomObjects);
    }

    /**
     * Creates a DomContent object containing HTML using a mapping function on a collection
     * Intended usage: {@literal each(numbers, n -> li(n.toString()))}
     *
     * @param <T>        The derived generic parameter type
     * @param collection the collection to iterate over, ex: a list of values "1, 2, 3"
     * @param mapper     the mapping function, ex: {@literal "n -> li(n.toString())"}
     * @return rawHtml containing mapped data {@literal (ex. docs: <li>1</li><li>2</li><li>3</li>)}
     */
    public static <T> DomContent each(Collection<T> collection, Function<? super T, DomContent> mapper) {
        return rawHtml(collection.stream().map(mapper.andThen(DomContent::render)).collect(Collectors.joining()));
    }

    /**
     * Filters a collection to a list, to be used with {@link j2html.TagCreator#each}
     * Intended usage: {@literal each(filter(numbers, n -> n % 2 == 0), n -> li(n.toString()))}
     *
     * @param <T>        The derived generic parameter type
     * @param collection the collection to filter, ex: a list of values "1, 2, 3"
     * @param filter     the filter predicate, {@literal ex: "n -> n % 2 == 0"}
     * @return the filtered collection as a list (ex. docs: 2)
     */
    public static <T> List<T> filter(Collection<T> collection, Predicate<? super T> filter) {
        return collection.stream().filter(filter).collect(Collectors.toList());
    }

    /**
     * Wraps a String in an UnescapedText element
     *
     * @param html the input html
     * @return the input html wrapped in an UnescapedText element
     */
    public static UnescapedText rawHtml(String html) {
        return new UnescapedText(html);
    }

    /**
     * Wraps a String in a Text element (does html-escaping)
     *
     * @param text the input string
     * @return the input string, html-escaped
     */
    public static Text text(String text) {
        return new Text(text);
    }

    /**
     * Return a complete html document string
     *
     * @param htmlTag the html content of a website
     * @return document declaration and rendered html content
     */
    public static String document(ContainerTag htmlTag) {
        if (htmlTag.getTagName().equals("html")) {
            return new EmptyTag("!DOCTYPE html").render() + htmlTag.render();
        }
        throw new IllegalArgumentException("Only HTML-tag can follow document declaration");
    }

    //Special tags
    public static ContainerTag tag(String tagName)          { return new ContainerTag(tagName); }
    public static EmptyTag emptyTag(String tagName)         { return new EmptyTag(tagName); }

    public static Text fileAsEscapedString(String path)     { return text(InlineStaticResource.getFileAsString(path)); }
    public static UnescapedText fileAsString(String path)   { return rawHtml(InlineStaticResource.getFileAsString(path)); }

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


    //ContainerTags (these methods are generated in TagCreatorCodeGenerator.java)
    public static ContainerTag a()                              { return new ContainerTag("a"); }
    public static ContainerTag a(String text)                   { return new ContainerTag("a").withText(text); }
    public static ContainerTag a(DomContent... dc)              { return new ContainerTag("a").with(dc); }

    public static ContainerTag abbr()                           { return new ContainerTag("abbr"); }
    public static ContainerTag abbr(String text)                { return new ContainerTag("abbr").withText(text); }
    public static ContainerTag abbr(DomContent... dc)           { return new ContainerTag("abbr").with(dc); }

    public static ContainerTag address()                        { return new ContainerTag("address"); }
    public static ContainerTag address(String text)             { return new ContainerTag("address").withText(text); }
    public static ContainerTag address(DomContent... dc)        { return new ContainerTag("address").with(dc); }

    public static ContainerTag article()                        { return new ContainerTag("article"); }
    public static ContainerTag article(String text)             { return new ContainerTag("article").withText(text); }
    public static ContainerTag article(DomContent... dc)        { return new ContainerTag("article").with(dc); }

    public static ContainerTag aside()                          { return new ContainerTag("aside"); }
    public static ContainerTag aside(String text)               { return new ContainerTag("aside").withText(text); }
    public static ContainerTag aside(DomContent... dc)          { return new ContainerTag("aside").with(dc); }

    public static ContainerTag audio()                          { return new ContainerTag("audio"); }
    public static ContainerTag audio(String text)               { return new ContainerTag("audio").withText(text); }
    public static ContainerTag audio(DomContent... dc)          { return new ContainerTag("audio").with(dc); }

    public static ContainerTag b()                              { return new ContainerTag("b"); }
    public static ContainerTag b(String text)                   { return new ContainerTag("b").withText(text); }
    public static ContainerTag b(DomContent... dc)              { return new ContainerTag("b").with(dc); }

    public static ContainerTag bdi()                            { return new ContainerTag("bdi"); }
    public static ContainerTag bdi(String text)                 { return new ContainerTag("bdi").withText(text); }
    public static ContainerTag bdi(DomContent... dc)            { return new ContainerTag("bdi").with(dc); }

    public static ContainerTag bdo()                            { return new ContainerTag("bdo"); }
    public static ContainerTag bdo(String text)                 { return new ContainerTag("bdo").withText(text); }
    public static ContainerTag bdo(DomContent... dc)            { return new ContainerTag("bdo").with(dc); }

    public static ContainerTag blockquote()                     { return new ContainerTag("blockquote"); }
    public static ContainerTag blockquote(String text)          { return new ContainerTag("blockquote").withText(text); }
    public static ContainerTag blockquote(DomContent... dc)     { return new ContainerTag("blockquote").with(dc); }

    public static ContainerTag body()                           { return new ContainerTag("body"); }
    public static ContainerTag body(String text)                { return new ContainerTag("body").withText(text); }
    public static ContainerTag body(DomContent... dc)           { return new ContainerTag("body").with(dc); }

    public static ContainerTag button()                         { return new ContainerTag("button"); }
    public static ContainerTag button(String text)              { return new ContainerTag("button").withText(text); }
    public static ContainerTag button(DomContent... dc)         { return new ContainerTag("button").with(dc); }

    public static ContainerTag canvas()                         { return new ContainerTag("canvas"); }
    public static ContainerTag canvas(String text)              { return new ContainerTag("canvas").withText(text); }
    public static ContainerTag canvas(DomContent... dc)         { return new ContainerTag("canvas").with(dc); }

    public static ContainerTag caption()                        { return new ContainerTag("caption"); }
    public static ContainerTag caption(String text)             { return new ContainerTag("caption").withText(text); }
    public static ContainerTag caption(DomContent... dc)        { return new ContainerTag("caption").with(dc); }

    public static ContainerTag cite()                           { return new ContainerTag("cite"); }
    public static ContainerTag cite(String text)                { return new ContainerTag("cite").withText(text); }
    public static ContainerTag cite(DomContent... dc)           { return new ContainerTag("cite").with(dc); }

    public static ContainerTag code()                           { return new ContainerTag("code"); }
    public static ContainerTag code(String text)                { return new ContainerTag("code").withText(text); }
    public static ContainerTag code(DomContent... dc)           { return new ContainerTag("code").with(dc); }

    public static ContainerTag colgroup()                       { return new ContainerTag("colgroup"); }
    public static ContainerTag colgroup(String text)            { return new ContainerTag("colgroup").withText(text); }
    public static ContainerTag colgroup(DomContent... dc)       { return new ContainerTag("colgroup").with(dc); }

    public static ContainerTag datalist()                       { return new ContainerTag("datalist"); }
    public static ContainerTag datalist(String text)            { return new ContainerTag("datalist").withText(text); }
    public static ContainerTag datalist(DomContent... dc)       { return new ContainerTag("datalist").with(dc); }

    public static ContainerTag dd()                             { return new ContainerTag("dd"); }
    public static ContainerTag dd(String text)                  { return new ContainerTag("dd").withText(text); }
    public static ContainerTag dd(DomContent... dc)             { return new ContainerTag("dd").with(dc); }

    public static ContainerTag del()                            { return new ContainerTag("del"); }
    public static ContainerTag del(String text)                 { return new ContainerTag("del").withText(text); }
    public static ContainerTag del(DomContent... dc)            { return new ContainerTag("del").with(dc); }

    public static ContainerTag details()                        { return new ContainerTag("details"); }
    public static ContainerTag details(String text)             { return new ContainerTag("details").withText(text); }
    public static ContainerTag details(DomContent... dc)        { return new ContainerTag("details").with(dc); }

    public static ContainerTag dfn()                            { return new ContainerTag("dfn"); }
    public static ContainerTag dfn(String text)                 { return new ContainerTag("dfn").withText(text); }
    public static ContainerTag dfn(DomContent... dc)            { return new ContainerTag("dfn").with(dc); }

    public static ContainerTag dialog()                         { return new ContainerTag("dialog"); }
    public static ContainerTag dialog(String text)              { return new ContainerTag("dialog").withText(text); }
    public static ContainerTag dialog(DomContent... dc)         { return new ContainerTag("dialog").with(dc); }

    public static ContainerTag div()                            { return new ContainerTag("div"); }
    public static ContainerTag div(String text)                 { return new ContainerTag("div").withText(text); }
    public static ContainerTag div(DomContent... dc)            { return new ContainerTag("div").with(dc); }

    public static ContainerTag dl()                             { return new ContainerTag("dl"); }
    public static ContainerTag dl(String text)                  { return new ContainerTag("dl").withText(text); }
    public static ContainerTag dl(DomContent... dc)             { return new ContainerTag("dl").with(dc); }

    public static ContainerTag dt()                             { return new ContainerTag("dt"); }
    public static ContainerTag dt(String text)                  { return new ContainerTag("dt").withText(text); }
    public static ContainerTag dt(DomContent... dc)             { return new ContainerTag("dt").with(dc); }

    public static ContainerTag em()                             { return new ContainerTag("em"); }
    public static ContainerTag em(String text)                  { return new ContainerTag("em").withText(text); }
    public static ContainerTag em(DomContent... dc)             { return new ContainerTag("em").with(dc); }

    public static ContainerTag fieldset()                       { return new ContainerTag("fieldset"); }
    public static ContainerTag fieldset(String text)            { return new ContainerTag("fieldset").withText(text); }
    public static ContainerTag fieldset(DomContent... dc)       { return new ContainerTag("fieldset").with(dc); }

    public static ContainerTag figcaption()                     { return new ContainerTag("figcaption"); }
    public static ContainerTag figcaption(String text)          { return new ContainerTag("figcaption").withText(text); }
    public static ContainerTag figcaption(DomContent... dc)     { return new ContainerTag("figcaption").with(dc); }

    public static ContainerTag figure()                         { return new ContainerTag("figure"); }
    public static ContainerTag figure(String text)              { return new ContainerTag("figure").withText(text); }
    public static ContainerTag figure(DomContent... dc)         { return new ContainerTag("figure").with(dc); }

    public static ContainerTag footer()                         { return new ContainerTag("footer"); }
    public static ContainerTag footer(String text)              { return new ContainerTag("footer").withText(text); }
    public static ContainerTag footer(DomContent... dc)         { return new ContainerTag("footer").with(dc); }

    public static ContainerTag form()                           { return new ContainerTag("form"); }
    public static ContainerTag form(String text)                { return new ContainerTag("form").withText(text); }
    public static ContainerTag form(DomContent... dc)           { return new ContainerTag("form").with(dc); }

    public static ContainerTag h1()                             { return new ContainerTag("h1"); }
    public static ContainerTag h1(String text)                  { return new ContainerTag("h1").withText(text); }
    public static ContainerTag h1(DomContent... dc)             { return new ContainerTag("h1").with(dc); }

    public static ContainerTag h2()                             { return new ContainerTag("h2"); }
    public static ContainerTag h2(String text)                  { return new ContainerTag("h2").withText(text); }
    public static ContainerTag h2(DomContent... dc)             { return new ContainerTag("h2").with(dc); }

    public static ContainerTag h3()                             { return new ContainerTag("h3"); }
    public static ContainerTag h3(String text)                  { return new ContainerTag("h3").withText(text); }
    public static ContainerTag h3(DomContent... dc)             { return new ContainerTag("h3").with(dc); }

    public static ContainerTag h4()                             { return new ContainerTag("h4"); }
    public static ContainerTag h4(String text)                  { return new ContainerTag("h4").withText(text); }
    public static ContainerTag h4(DomContent... dc)             { return new ContainerTag("h4").with(dc); }

    public static ContainerTag h5()                             { return new ContainerTag("h5"); }
    public static ContainerTag h5(String text)                  { return new ContainerTag("h5").withText(text); }
    public static ContainerTag h5(DomContent... dc)             { return new ContainerTag("h5").with(dc); }

    public static ContainerTag h6()                             { return new ContainerTag("h6"); }
    public static ContainerTag h6(String text)                  { return new ContainerTag("h6").withText(text); }
    public static ContainerTag h6(DomContent... dc)             { return new ContainerTag("h6").with(dc); }

    public static ContainerTag head()                           { return new ContainerTag("head"); }
    public static ContainerTag head(String text)                { return new ContainerTag("head").withText(text); }
    public static ContainerTag head(DomContent... dc)           { return new ContainerTag("head").with(dc); }

    public static ContainerTag header()                         { return new ContainerTag("header"); }
    public static ContainerTag header(String text)              { return new ContainerTag("header").withText(text); }
    public static ContainerTag header(DomContent... dc)         { return new ContainerTag("header").with(dc); }

    public static ContainerTag html()                           { return new ContainerTag("html"); }
    public static ContainerTag html(String text)                { return new ContainerTag("html").withText(text); }
    public static ContainerTag html(DomContent... dc)           { return new ContainerTag("html").with(dc); }

    public static ContainerTag i()                              { return new ContainerTag("i"); }
    public static ContainerTag i(String text)                   { return new ContainerTag("i").withText(text); }
    public static ContainerTag i(DomContent... dc)              { return new ContainerTag("i").with(dc); }

    public static ContainerTag iframe()                         { return new ContainerTag("iframe"); }
    public static ContainerTag iframe(String text)              { return new ContainerTag("iframe").withText(text); }
    public static ContainerTag iframe(DomContent... dc)         { return new ContainerTag("iframe").with(dc); }

    public static ContainerTag ins()                            { return new ContainerTag("ins"); }
    public static ContainerTag ins(String text)                 { return new ContainerTag("ins").withText(text); }
    public static ContainerTag ins(DomContent... dc)            { return new ContainerTag("ins").with(dc); }

    public static ContainerTag kbd()                            { return new ContainerTag("kbd"); }
    public static ContainerTag kbd(String text)                 { return new ContainerTag("kbd").withText(text); }
    public static ContainerTag kbd(DomContent... dc)            { return new ContainerTag("kbd").with(dc); }

    public static ContainerTag label()                          { return new ContainerTag("label"); }
    public static ContainerTag label(String text)               { return new ContainerTag("label").withText(text); }
    public static ContainerTag label(DomContent... dc)          { return new ContainerTag("label").with(dc); }

    public static ContainerTag legend()                         { return new ContainerTag("legend"); }
    public static ContainerTag legend(String text)              { return new ContainerTag("legend").withText(text); }
    public static ContainerTag legend(DomContent... dc)         { return new ContainerTag("legend").with(dc); }

    public static ContainerTag li()                             { return new ContainerTag("li"); }
    public static ContainerTag li(String text)                  { return new ContainerTag("li").withText(text); }
    public static ContainerTag li(DomContent... dc)             { return new ContainerTag("li").with(dc); }

    public static ContainerTag main()                           { return new ContainerTag("main"); }
    public static ContainerTag main(String text)                { return new ContainerTag("main").withText(text); }
    public static ContainerTag main(DomContent... dc)           { return new ContainerTag("main").with(dc); }

    public static ContainerTag map()                            { return new ContainerTag("map"); }
    public static ContainerTag map(String text)                 { return new ContainerTag("map").withText(text); }
    public static ContainerTag map(DomContent... dc)            { return new ContainerTag("map").with(dc); }

    public static ContainerTag mark()                           { return new ContainerTag("mark"); }
    public static ContainerTag mark(String text)                { return new ContainerTag("mark").withText(text); }
    public static ContainerTag mark(DomContent... dc)           { return new ContainerTag("mark").with(dc); }

    public static ContainerTag menu()                           { return new ContainerTag("menu"); }
    public static ContainerTag menu(String text)                { return new ContainerTag("menu").withText(text); }
    public static ContainerTag menu(DomContent... dc)           { return new ContainerTag("menu").with(dc); }

    public static ContainerTag menuitem()                       { return new ContainerTag("menuitem"); }
    public static ContainerTag menuitem(String text)            { return new ContainerTag("menuitem").withText(text); }
    public static ContainerTag menuitem(DomContent... dc)       { return new ContainerTag("menuitem").with(dc); }

    public static ContainerTag meter()                          { return new ContainerTag("meter"); }
    public static ContainerTag meter(String text)               { return new ContainerTag("meter").withText(text); }
    public static ContainerTag meter(DomContent... dc)          { return new ContainerTag("meter").with(dc); }

    public static ContainerTag nav()                            { return new ContainerTag("nav"); }
    public static ContainerTag nav(String text)                 { return new ContainerTag("nav").withText(text); }
    public static ContainerTag nav(DomContent... dc)            { return new ContainerTag("nav").with(dc); }

    public static ContainerTag noscript()                       { return new ContainerTag("noscript"); }
    public static ContainerTag noscript(String text)            { return new ContainerTag("noscript").withText(text); }
    public static ContainerTag noscript(DomContent... dc)       { return new ContainerTag("noscript").with(dc); }

    public static ContainerTag object()                         { return new ContainerTag("object"); }
    public static ContainerTag object(String text)              { return new ContainerTag("object").withText(text); }
    public static ContainerTag object(DomContent... dc)         { return new ContainerTag("object").with(dc); }

    public static ContainerTag ol()                             { return new ContainerTag("ol"); }
    public static ContainerTag ol(String text)                  { return new ContainerTag("ol").withText(text); }
    public static ContainerTag ol(DomContent... dc)             { return new ContainerTag("ol").with(dc); }

    public static ContainerTag optgroup()                       { return new ContainerTag("optgroup"); }
    public static ContainerTag optgroup(String text)            { return new ContainerTag("optgroup").withText(text); }
    public static ContainerTag optgroup(DomContent... dc)       { return new ContainerTag("optgroup").with(dc); }

    public static ContainerTag option()                         { return new ContainerTag("option"); }
    public static ContainerTag option(String text)              { return new ContainerTag("option").withText(text); }
    public static ContainerTag option(DomContent... dc)         { return new ContainerTag("option").with(dc); }

    public static ContainerTag output()                         { return new ContainerTag("output"); }
    public static ContainerTag output(String text)              { return new ContainerTag("output").withText(text); }
    public static ContainerTag output(DomContent... dc)         { return new ContainerTag("output").with(dc); }

    public static ContainerTag p()                              { return new ContainerTag("p"); }
    public static ContainerTag p(String text)                   { return new ContainerTag("p").withText(text); }
    public static ContainerTag p(DomContent... dc)              { return new ContainerTag("p").with(dc); }

    public static ContainerTag pre()                            { return new ContainerTag("pre"); }
    public static ContainerTag pre(String text)                 { return new ContainerTag("pre").withText(text); }
    public static ContainerTag pre(DomContent... dc)            { return new ContainerTag("pre").with(dc); }

    public static ContainerTag progress()                       { return new ContainerTag("progress"); }
    public static ContainerTag progress(String text)            { return new ContainerTag("progress").withText(text); }
    public static ContainerTag progress(DomContent... dc)       { return new ContainerTag("progress").with(dc); }

    public static ContainerTag q()                              { return new ContainerTag("q"); }
    public static ContainerTag q(String text)                   { return new ContainerTag("q").withText(text); }
    public static ContainerTag q(DomContent... dc)              { return new ContainerTag("q").with(dc); }

    public static ContainerTag rp()                             { return new ContainerTag("rp"); }
    public static ContainerTag rp(String text)                  { return new ContainerTag("rp").withText(text); }
    public static ContainerTag rp(DomContent... dc)             { return new ContainerTag("rp").with(dc); }

    public static ContainerTag rt()                             { return new ContainerTag("rt"); }
    public static ContainerTag rt(String text)                  { return new ContainerTag("rt").withText(text); }
    public static ContainerTag rt(DomContent... dc)             { return new ContainerTag("rt").with(dc); }

    public static ContainerTag ruby()                           { return new ContainerTag("ruby"); }
    public static ContainerTag ruby(String text)                { return new ContainerTag("ruby").withText(text); }
    public static ContainerTag ruby(DomContent... dc)           { return new ContainerTag("ruby").with(dc); }

    public static ContainerTag s()                              { return new ContainerTag("s"); }
    public static ContainerTag s(String text)                   { return new ContainerTag("s").withText(text); }
    public static ContainerTag s(DomContent... dc)              { return new ContainerTag("s").with(dc); }

    public static ContainerTag samp()                           { return new ContainerTag("samp"); }
    public static ContainerTag samp(String text)                { return new ContainerTag("samp").withText(text); }
    public static ContainerTag samp(DomContent... dc)           { return new ContainerTag("samp").with(dc); }

    public static ContainerTag script()                         { return new ContainerTag("script"); }
    public static ContainerTag script(String text)              { return new ContainerTag("script").withText(text); }
    public static ContainerTag script(DomContent... dc)         { return new ContainerTag("script").with(dc); }

    public static ContainerTag section()                        { return new ContainerTag("section"); }
    public static ContainerTag section(String text)             { return new ContainerTag("section").withText(text); }
    public static ContainerTag section(DomContent... dc)        { return new ContainerTag("section").with(dc); }

    public static ContainerTag select()                         { return new ContainerTag("select"); }
    public static ContainerTag select(String text)              { return new ContainerTag("select").withText(text); }
    public static ContainerTag select(DomContent... dc)         { return new ContainerTag("select").with(dc); }

    public static ContainerTag small()                          { return new ContainerTag("small"); }
    public static ContainerTag small(String text)               { return new ContainerTag("small").withText(text); }
    public static ContainerTag small(DomContent... dc)          { return new ContainerTag("small").with(dc); }

    public static ContainerTag span()                           { return new ContainerTag("span"); }
    public static ContainerTag span(String text)                { return new ContainerTag("span").withText(text); }
    public static ContainerTag span(DomContent... dc)           { return new ContainerTag("span").with(dc); }

    public static ContainerTag strong()                         { return new ContainerTag("strong"); }
    public static ContainerTag strong(String text)              { return new ContainerTag("strong").withText(text); }
    public static ContainerTag strong(DomContent... dc)         { return new ContainerTag("strong").with(dc); }

    public static ContainerTag style()                          { return new ContainerTag("style"); }
    public static ContainerTag style(String text)               { return new ContainerTag("style").withText(text); }
    public static ContainerTag style(DomContent... dc)          { return new ContainerTag("style").with(dc); }

    public static ContainerTag sub()                            { return new ContainerTag("sub"); }
    public static ContainerTag sub(String text)                 { return new ContainerTag("sub").withText(text); }
    public static ContainerTag sub(DomContent... dc)            { return new ContainerTag("sub").with(dc); }

    public static ContainerTag summary()                        { return new ContainerTag("summary"); }
    public static ContainerTag summary(String text)             { return new ContainerTag("summary").withText(text); }
    public static ContainerTag summary(DomContent... dc)        { return new ContainerTag("summary").with(dc); }

    public static ContainerTag sup()                            { return new ContainerTag("sup"); }
    public static ContainerTag sup(String text)                 { return new ContainerTag("sup").withText(text); }
    public static ContainerTag sup(DomContent... dc)            { return new ContainerTag("sup").with(dc); }

    public static ContainerTag table()                          { return new ContainerTag("table"); }
    public static ContainerTag table(String text)               { return new ContainerTag("table").withText(text); }
    public static ContainerTag table(DomContent... dc)          { return new ContainerTag("table").with(dc); }

    public static ContainerTag tbody()                          { return new ContainerTag("tbody"); }
    public static ContainerTag tbody(String text)               { return new ContainerTag("tbody").withText(text); }
    public static ContainerTag tbody(DomContent... dc)          { return new ContainerTag("tbody").with(dc); }

    public static ContainerTag td()                             { return new ContainerTag("td"); }
    public static ContainerTag td(String text)                  { return new ContainerTag("td").withText(text); }
    public static ContainerTag td(DomContent... dc)             { return new ContainerTag("td").with(dc); }

    public static ContainerTag textarea()                       { return new ContainerTag("textarea"); }
    public static ContainerTag textarea(String text)            { return new ContainerTag("textarea").withText(text); }
    public static ContainerTag textarea(DomContent... dc)       { return new ContainerTag("textarea").with(dc); }

    public static ContainerTag tfoot()                          { return new ContainerTag("tfoot"); }
    public static ContainerTag tfoot(String text)               { return new ContainerTag("tfoot").withText(text); }
    public static ContainerTag tfoot(DomContent... dc)          { return new ContainerTag("tfoot").with(dc); }

    public static ContainerTag th()                             { return new ContainerTag("th"); }
    public static ContainerTag th(String text)                  { return new ContainerTag("th").withText(text); }
    public static ContainerTag th(DomContent... dc)             { return new ContainerTag("th").with(dc); }

    public static ContainerTag thead()                          { return new ContainerTag("thead"); }
    public static ContainerTag thead(String text)               { return new ContainerTag("thead").withText(text); }
    public static ContainerTag thead(DomContent... dc)          { return new ContainerTag("thead").with(dc); }

    public static ContainerTag time()                           { return new ContainerTag("time"); }
    public static ContainerTag time(String text)                { return new ContainerTag("time").withText(text); }
    public static ContainerTag time(DomContent... dc)           { return new ContainerTag("time").with(dc); }

    public static ContainerTag title()                          { return new ContainerTag("title"); }
    public static ContainerTag title(String text)               { return new ContainerTag("title").withText(text); }
    public static ContainerTag title(DomContent... dc)          { return new ContainerTag("title").with(dc); }

    public static ContainerTag tr()                             { return new ContainerTag("tr"); }
    public static ContainerTag tr(String text)                  { return new ContainerTag("tr").withText(text); }
    public static ContainerTag tr(DomContent... dc)             { return new ContainerTag("tr").with(dc); }

    public static ContainerTag u()                              { return new ContainerTag("u"); }
    public static ContainerTag u(String text)                   { return new ContainerTag("u").withText(text); }
    public static ContainerTag u(DomContent... dc)              { return new ContainerTag("u").with(dc); }

    public static ContainerTag ul()                             { return new ContainerTag("ul"); }
    public static ContainerTag ul(String text)                  { return new ContainerTag("ul").withText(text); }
    public static ContainerTag ul(DomContent... dc)             { return new ContainerTag("ul").with(dc); }

    public static ContainerTag var()                            { return new ContainerTag("var"); }
    public static ContainerTag var(String text)                 { return new ContainerTag("var").withText(text); }
    public static ContainerTag var(DomContent... dc)            { return new ContainerTag("var").with(dc); }

    public static ContainerTag video()                          { return new ContainerTag("video"); }
    public static ContainerTag video(String text)               { return new ContainerTag("video").withText(text); }
    public static ContainerTag video(DomContent... dc)          { return new ContainerTag("video").with(dc); }

}
