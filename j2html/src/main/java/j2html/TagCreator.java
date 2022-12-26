package j2html;

import j2html.attributes.Attr;
import j2html.tags.ContainerTag;
import j2html.tags.DomContent;
import j2html.tags.DomContentJoiner;
import j2html.tags.EmptyTag;
import j2html.tags.InlineStaticResource;
import j2html.tags.Tag;
import j2html.tags.Text;
import j2html.tags.UnescapedText;
import j2html.tags.specialized.*;

import java.util.*;
import java.util.Map.Entry;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TagCreator {

    private TagCreator() {
    }

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
     * Generic if-expression to if'ing inside method calls
     *
     * @param optional   The item that may be present
     * @param ifFunction The function that will be called if that optional is present
     * @param <T>        The derived generic parameter type
     * @param <U>        The supplying generic parameter type
     * @return transformed value if condition is true, null otherwise
     */
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    public static <T, U> T iff(Optional<U> optional, Function<U, T> ifFunction) {
        if (Objects.nonNull(optional) && optional.isPresent()) {
            return optional.map(ifFunction).orElse(null);
        }
        return null;
    }

    /**
     * Like {@link j2html.TagCreator#iff}, but returns else-value instead of null
     */
    public static <T> T iffElse(boolean condition, T ifValue, T elseValue) {
        return condition ? ifValue : elseValue;
    }

    /**
     * Returns a Attr.ShortForm object with either id, classes or both,
     * obtained from parsing the input string using css selector syntax
     *
     * @param attrs the string with shortform attributes, only id and class is supported
     * @return a Attr.ShortForm object
     */
    public static Attr.ShortForm attrs(String attrs) {
        return Attr.shortFormFromAttrsString(attrs);
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
     * Creates a group of DomContent objects (that may be used with {@link #iff(boolean, Object)} or returned from a
     * method call).
     *
     * @param contents the DomContent elements
     * @return DomContent containing the given elements
     */
    public static DomContent each(DomContent... contents) {
        return new ContainerTag<>(null).with(contents);
    }

    /**
     * Creates a DomContent object containing HTML elements from a stream.
     * Intended usage: {@literal each(numbers.stream().map(n -> li(n.toString())))}
     *
     * @param stream the stream of DomContent elements
     * @return DomContent containing elements from the stream
     */
    public static DomContent each(Stream<DomContent> stream) {
        return new ContainerTag<>(null).with(stream);
    }

    /**
     * Creates a DomContent object containing HTML using a mapping function on a collection
     * Intended usage: {@literal each(numbers, n -> li(n.toString()))}
     *
     * @param <T>        The derived generic parameter type
     * @param collection the collection to iterate over, ex: a list of values "1, 2, 3"
     * @param mapper     the mapping function, ex: {@literal "n -> li(n.toString())"}
     * @return DomContent containing mapped data {@literal (ex. docs: [li(1), li(2), li(3)])}
     */
    public static <T> DomContent each(Collection<T> collection, Function<? super T, DomContent> mapper) {
        return tag(null).with(collection.stream().map(mapper));
    }

    /**
     * Creates a DomContent object containing HTML using a mapping BiFunction on a collection
     * Intended usage: {@literal each(names, (index, name) -> li(index + " " + name))}
     *
     * @param <T>        The derived generic parameter type
     * @param collection the collection to iterate over, ex: a list of values [ "Tom", "Dick", "Harry" ]
     * @param mapper     the mapping BiFunction, ex: {@literal "(index, name) -> li(index + " " + name)"}
     * @return DomContent containing mapped data {@literal (ex. docs: [li(0 Tom), li(1 Dick), li(2 Harry)])}
     */
    public static <T> DomContent each(Collection<T> collection, BiFunction<Integer, ? super T, DomContent> mapper) {
        ContainerTag<?> dom = tag(null);
        int i = 0;
        for(T t : collection){
            dom.with(mapper.apply(i++, t));
        }
        return dom;
    }

    public static <I, T> DomContent each(final Map<I, T> map, final Function<Entry<I, T>, DomContent> mapper) {
        return each(map.entrySet().stream().map(mapper));
    }

    /**
     * Creates a DomContent object containing HTML using a mapping function on a map
     * Intended usage: {@literal each(idsToNames, (id, name) -> li(id + " " + name))}
     *
     * @param <I>    The type of the keys
     * @param <T>    The type of the values
     * @param map    the map to iterate over, ex: a map of values { 1: "Tom", 2: "Dick", 3: "Harry" }
     * @param mapper the mapping function, ex: {@literal "(id, name) -> li(id + " " + name)"}
     * @return DomContent containing mapped data {@literal (ex. docs: [li(1 Tom), li(2 Dick), li(3 Harry)])}
     */
    public static <I, T> DomContent each(final Map<I, T> map, final BiFunction<I, T, DomContent> mapper) {
        return each(map.entrySet().stream().map(entry -> mapper.apply(entry.getKey(), entry.getValue())));
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
    public static String document(HtmlTag htmlTag) {
        return document().render() + htmlTag.render();
    }

    //Special tags
    public static ContainerTag<? extends Tag<?>> tag(String tagName) {
        return new ContainerTag<>(tagName);
    }

    public static EmptyTag<? extends Tag<?>> emptyTag(String tagName) {
        return new EmptyTag<>(tagName);
    }

    public static Text fileAsEscapedString(String path) {
        return text(InlineStaticResource.getFileAsString(path));
    }

    public static UnescapedText fileAsString(String path) {
        return rawHtml(InlineStaticResource.getFileAsString(path));
    }

    public static StyleTag styleWithInlineFile(String path) {
        return (StyleTag) InlineStaticResource.get(path, InlineStaticResource.TargetFormat.CSS);
    }

    public static ScriptTag scriptWithInlineFile(String path) {
        return (ScriptTag) InlineStaticResource.get(path, InlineStaticResource.TargetFormat.JS);
    }

    public static StyleTag styleWithInlineFile_min(String path) {
        return (StyleTag) InlineStaticResource.get(path, InlineStaticResource.TargetFormat.CSS_MIN);
    }

    public static ScriptTag scriptWithInlineFile_min(String path) {
        return (ScriptTag) InlineStaticResource.get(path, InlineStaticResource.TargetFormat.JS_MIN);
    }

    public static DomContent document() {
        return rawHtml("<!DOCTYPE html>");
    }

    public static HtmlTag html() {
        return new HtmlTag();
    }

    public static HtmlTag html(String text) { return html().withText(text); }

    public static HtmlTag html(DomContent... dc) { return html().with(dc); }

    public static HtmlTag html(Attr.ShortForm shortAttr) {
        return Attr.addTo(html(), shortAttr);
    }

    public static HtmlTag html(Attr.ShortForm shortAttr, String text) {
        return Attr.addTo(html(text), shortAttr);
    }

    public static HtmlTag html(Attr.ShortForm shortAttr, DomContent... dc) {
        return Attr.addTo(html(dc), shortAttr);
    }

    public static BodyTag body() {
        return new BodyTag();
    }

    public static BodyTag body(String text) {
        return new BodyTag().withText(text);
    }

    public static BodyTag body(DomContent... dc) {
        return new BodyTag().with(dc);
    }

    public static BodyTag body(Attr.ShortForm shortAttr) {
        return Attr.addTo(new BodyTag(), shortAttr);
    }

    public static BodyTag body(Attr.ShortForm shortAttr, String text) {
        return Attr.addTo(new BodyTag().withText(text), shortAttr);
    }

    public static BodyTag body(Attr.ShortForm shortAttr, DomContent... dc) {
        return Attr.addTo(new BodyTag().with(dc), shortAttr);
    }

    public static HeadTag head() {
        return new HeadTag();
    }

    public static HeadTag head(String text) {
        return new HeadTag().withText(text);
    }

    public static HeadTag head(DomContent... dc) {
        return new HeadTag().with(dc);
    }

    public static HeadTag head(Attr.ShortForm shortAttr) {
        return Attr.addTo(new HeadTag(), shortAttr);
    }

    public static HeadTag head(Attr.ShortForm shortAttr, String text) {
        return Attr.addTo(new HeadTag().withText(text), shortAttr);
    }

    public static HeadTag head(Attr.ShortForm shortAttr, DomContent... dc) {
        return Attr.addTo(new HeadTag().with(dc), shortAttr);
    }

    // EmptyTags, generated in class j2html.tags.generators.TagCreatorCodeGenerator
    public static AreaTag area ()                                                   { return  new AreaTag(); }
    public static AreaTag area (Attr.ShortForm shortAttr)                           {  return Attr.addTo( new AreaTag(), shortAttr); }

    public static BaseTag base ()                                                   { return  new BaseTag(); }
    public static BaseTag base (Attr.ShortForm shortAttr)                           {  return Attr.addTo( new BaseTag(), shortAttr); }

    public static BrTag br ()                                                       { return  new BrTag(); }
    public static BrTag br (Attr.ShortForm shortAttr)                               {  return Attr.addTo( new BrTag(), shortAttr); }

    public static ColTag col ()                                                     { return  new ColTag(); }
    public static ColTag col (Attr.ShortForm shortAttr)                             {  return Attr.addTo( new ColTag(), shortAttr); }

    public static EmbedTag embed ()                                                 { return  new EmbedTag(); }
    public static EmbedTag embed (Attr.ShortForm shortAttr)                         {  return Attr.addTo( new EmbedTag(), shortAttr); }

    public static HrTag hr ()                                                       { return  new HrTag(); }
    public static HrTag hr (Attr.ShortForm shortAttr)                               {  return Attr.addTo( new HrTag(), shortAttr); }

    public static ImgTag img ()                                                     { return  new ImgTag(); }
    public static ImgTag img (Attr.ShortForm shortAttr)                             {  return Attr.addTo( new ImgTag(), shortAttr); }

    public static InputTag input ()                                                 { return  new InputTag(); }
    public static InputTag input (Attr.ShortForm shortAttr)                         {  return Attr.addTo( new InputTag(), shortAttr); }

    public static KeygenTag keygen ()                                               { return  new KeygenTag(); }
    public static KeygenTag keygen (Attr.ShortForm shortAttr)                       {  return Attr.addTo( new KeygenTag(), shortAttr); }

    public static LinkTag link ()                                                   { return  new LinkTag(); }
    public static LinkTag link (Attr.ShortForm shortAttr)                           {  return Attr.addTo( new LinkTag(), shortAttr); }

    public static MetaTag meta ()                                                   { return  new MetaTag(); }
    public static MetaTag meta (Attr.ShortForm shortAttr)                           {  return Attr.addTo( new MetaTag(), shortAttr); }

    public static ParamTag param ()                                                 { return  new ParamTag(); }
    public static ParamTag param (Attr.ShortForm shortAttr)                         {  return Attr.addTo( new ParamTag(), shortAttr); }

    public static SourceTag source ()                                               { return  new SourceTag(); }
    public static SourceTag source (Attr.ShortForm shortAttr)                       {  return Attr.addTo( new SourceTag(), shortAttr); }

    public static TrackTag track ()                                                 { return  new TrackTag(); }
    public static TrackTag track (Attr.ShortForm shortAttr)                         {  return Attr.addTo( new TrackTag(), shortAttr); }

    public static WbrTag wbr ()                                                     { return  new WbrTag(); }
    public static WbrTag wbr (Attr.ShortForm shortAttr)                             {  return Attr.addTo( new WbrTag(), shortAttr); }

    // ContainerTags, generated in class j2html.tags.generators.TagCreatorCodeGenerator
    public static ATag a ()                                                         {  return new ATag(); }
    public static ATag a (String text)                                              {  return new ATag().withText(text); }
    public static ATag a (DomContent... dc)                                         {  return new ATag().with(dc); }
    public static ATag a (Attr.ShortForm shortAttr)                                 {  return Attr.addTo( new ATag(), shortAttr); }
    public static ATag a (Attr.ShortForm shortAttr, String text)                    {  return Attr.addTo( new ATag().withText(text), shortAttr); }
    public static ATag a (Attr.ShortForm shortAttr, DomContent... dc)               {  return Attr.addTo( new ATag().with(dc), shortAttr); }

    public static AbbrTag abbr ()                                                   {  return new AbbrTag(); }
    public static AbbrTag abbr (String text)                                        {  return new AbbrTag().withText(text); }
    public static AbbrTag abbr (DomContent... dc)                                   {  return new AbbrTag().with(dc); }
    public static AbbrTag abbr (Attr.ShortForm shortAttr)                           {  return Attr.addTo( new AbbrTag(), shortAttr); }
    public static AbbrTag abbr (Attr.ShortForm shortAttr, String text)              {  return Attr.addTo( new AbbrTag().withText(text), shortAttr); }
    public static AbbrTag abbr (Attr.ShortForm shortAttr, DomContent... dc)         {  return Attr.addTo( new AbbrTag().with(dc), shortAttr); }

    public static AddressTag address ()                                             {  return new AddressTag(); }
    public static AddressTag address (String text)                                  {  return new AddressTag().withText(text); }
    public static AddressTag address (DomContent... dc)                             {  return new AddressTag().with(dc); }
    public static AddressTag address (Attr.ShortForm shortAttr)                     {  return Attr.addTo( new AddressTag(), shortAttr); }
    public static AddressTag address (Attr.ShortForm shortAttr, String text)        {  return Attr.addTo( new AddressTag().withText(text), shortAttr); }
    public static AddressTag address (Attr.ShortForm shortAttr, DomContent... dc)   {  return Attr.addTo( new AddressTag().with(dc), shortAttr); }

    public static ArticleTag article ()                                             {  return new ArticleTag(); }
    public static ArticleTag article (String text)                                  {  return new ArticleTag().withText(text); }
    public static ArticleTag article (DomContent... dc)                             {  return new ArticleTag().with(dc); }
    public static ArticleTag article (Attr.ShortForm shortAttr)                     {  return Attr.addTo( new ArticleTag(), shortAttr); }
    public static ArticleTag article (Attr.ShortForm shortAttr, String text)        {  return Attr.addTo( new ArticleTag().withText(text), shortAttr); }
    public static ArticleTag article (Attr.ShortForm shortAttr, DomContent... dc)   {  return Attr.addTo( new ArticleTag().with(dc), shortAttr); }

    public static AsideTag aside ()                                                 {  return new AsideTag(); }
    public static AsideTag aside (String text)                                      {  return new AsideTag().withText(text); }
    public static AsideTag aside (DomContent... dc)                                 {  return new AsideTag().with(dc); }
    public static AsideTag aside (Attr.ShortForm shortAttr)                         {  return Attr.addTo( new AsideTag(), shortAttr); }
    public static AsideTag aside (Attr.ShortForm shortAttr, String text)            {  return Attr.addTo( new AsideTag().withText(text), shortAttr); }
    public static AsideTag aside (Attr.ShortForm shortAttr, DomContent... dc)       {  return Attr.addTo( new AsideTag().with(dc), shortAttr); }

    public static AudioTag audio ()                                                 {  return new AudioTag(); }
    public static AudioTag audio (String text)                                      {  return new AudioTag().withText(text); }
    public static AudioTag audio (DomContent... dc)                                 {  return new AudioTag().with(dc); }
    public static AudioTag audio (Attr.ShortForm shortAttr)                         {  return Attr.addTo( new AudioTag(), shortAttr); }
    public static AudioTag audio (Attr.ShortForm shortAttr, String text)            {  return Attr.addTo( new AudioTag().withText(text), shortAttr); }
    public static AudioTag audio (Attr.ShortForm shortAttr, DomContent... dc)       {  return Attr.addTo( new AudioTag().with(dc), shortAttr); }

    public static BTag b ()                                                         {  return new BTag(); }
    public static BTag b (String text)                                              {  return new BTag().withText(text); }
    public static BTag b (DomContent... dc)                                         {  return new BTag().with(dc); }
    public static BTag b (Attr.ShortForm shortAttr)                                 {  return Attr.addTo( new BTag(), shortAttr); }
    public static BTag b (Attr.ShortForm shortAttr, String text)                    {  return Attr.addTo( new BTag().withText(text), shortAttr); }
    public static BTag b (Attr.ShortForm shortAttr, DomContent... dc)               {  return Attr.addTo( new BTag().with(dc), shortAttr); }

    public static BdiTag bdi ()                                                     {  return new BdiTag(); }
    public static BdiTag bdi (String text)                                          {  return new BdiTag().withText(text); }
    public static BdiTag bdi (DomContent... dc)                                     {  return new BdiTag().with(dc); }
    public static BdiTag bdi (Attr.ShortForm shortAttr)                             {  return Attr.addTo( new BdiTag(), shortAttr); }
    public static BdiTag bdi (Attr.ShortForm shortAttr, String text)                {  return Attr.addTo( new BdiTag().withText(text), shortAttr); }
    public static BdiTag bdi (Attr.ShortForm shortAttr, DomContent... dc)           {  return Attr.addTo( new BdiTag().with(dc), shortAttr); }

    public static BdoTag bdo ()                                                     {  return new BdoTag(); }
    public static BdoTag bdo (String text)                                          {  return new BdoTag().withText(text); }
    public static BdoTag bdo (DomContent... dc)                                     {  return new BdoTag().with(dc); }
    public static BdoTag bdo (Attr.ShortForm shortAttr)                             {  return Attr.addTo( new BdoTag(), shortAttr); }
    public static BdoTag bdo (Attr.ShortForm shortAttr, String text)                {  return Attr.addTo( new BdoTag().withText(text), shortAttr); }
    public static BdoTag bdo (Attr.ShortForm shortAttr, DomContent... dc)           {  return Attr.addTo( new BdoTag().with(dc), shortAttr); }

    public static BlockquoteTag blockquote ()                                       {  return new BlockquoteTag(); }
    public static BlockquoteTag blockquote (String text)                            {  return new BlockquoteTag().withText(text); }
    public static BlockquoteTag blockquote (DomContent... dc)                       {  return new BlockquoteTag().with(dc); }
    public static BlockquoteTag blockquote (Attr.ShortForm shortAttr)               {  return Attr.addTo( new BlockquoteTag(), shortAttr); }
    public static BlockquoteTag blockquote (Attr.ShortForm shortAttr, String text)  {  return Attr.addTo( new BlockquoteTag().withText(text), shortAttr); }
    public static BlockquoteTag blockquote (Attr.ShortForm shortAttr, DomContent... dc){  return Attr.addTo( new BlockquoteTag().with(dc), shortAttr); }

    public static ButtonTag button ()                                               {  return new ButtonTag(); }
    public static ButtonTag button (String text)                                    {  return new ButtonTag().withText(text); }
    public static ButtonTag button (DomContent... dc)                               {  return new ButtonTag().with(dc); }
    public static ButtonTag button (Attr.ShortForm shortAttr)                       {  return Attr.addTo( new ButtonTag(), shortAttr); }
    public static ButtonTag button (Attr.ShortForm shortAttr, String text)          {  return Attr.addTo( new ButtonTag().withText(text), shortAttr); }
    public static ButtonTag button (Attr.ShortForm shortAttr, DomContent... dc)     {  return Attr.addTo( new ButtonTag().with(dc), shortAttr); }

    public static CanvasTag canvas ()                                               {  return new CanvasTag(); }
    public static CanvasTag canvas (String text)                                    {  return new CanvasTag().withText(text); }
    public static CanvasTag canvas (DomContent... dc)                               {  return new CanvasTag().with(dc); }
    public static CanvasTag canvas (Attr.ShortForm shortAttr)                       {  return Attr.addTo( new CanvasTag(), shortAttr); }
    public static CanvasTag canvas (Attr.ShortForm shortAttr, String text)          {  return Attr.addTo( new CanvasTag().withText(text), shortAttr); }
    public static CanvasTag canvas (Attr.ShortForm shortAttr, DomContent... dc)     {  return Attr.addTo( new CanvasTag().with(dc), shortAttr); }

    public static CaptionTag caption ()                                             {  return new CaptionTag(); }
    public static CaptionTag caption (String text)                                  {  return new CaptionTag().withText(text); }
    public static CaptionTag caption (DomContent... dc)                             {  return new CaptionTag().with(dc); }
    public static CaptionTag caption (Attr.ShortForm shortAttr)                     {  return Attr.addTo( new CaptionTag(), shortAttr); }
    public static CaptionTag caption (Attr.ShortForm shortAttr, String text)        {  return Attr.addTo( new CaptionTag().withText(text), shortAttr); }
    public static CaptionTag caption (Attr.ShortForm shortAttr, DomContent... dc)   {  return Attr.addTo( new CaptionTag().with(dc), shortAttr); }

    public static CiteTag cite ()                                                   {  return new CiteTag(); }
    public static CiteTag cite (String text)                                        {  return new CiteTag().withText(text); }
    public static CiteTag cite (DomContent... dc)                                   {  return new CiteTag().with(dc); }
    public static CiteTag cite (Attr.ShortForm shortAttr)                           {  return Attr.addTo( new CiteTag(), shortAttr); }
    public static CiteTag cite (Attr.ShortForm shortAttr, String text)              {  return Attr.addTo( new CiteTag().withText(text), shortAttr); }
    public static CiteTag cite (Attr.ShortForm shortAttr, DomContent... dc)         {  return Attr.addTo( new CiteTag().with(dc), shortAttr); }

    public static CodeTag code ()                                                   {  return new CodeTag(); }
    public static CodeTag code (String text)                                        {  return new CodeTag().withText(text); }
    public static CodeTag code (DomContent... dc)                                   {  return new CodeTag().with(dc); }
    public static CodeTag code (Attr.ShortForm shortAttr)                           {  return Attr.addTo( new CodeTag(), shortAttr); }
    public static CodeTag code (Attr.ShortForm shortAttr, String text)              {  return Attr.addTo( new CodeTag().withText(text), shortAttr); }
    public static CodeTag code (Attr.ShortForm shortAttr, DomContent... dc)         {  return Attr.addTo( new CodeTag().with(dc), shortAttr); }

    public static ColgroupTag colgroup ()                                           {  return new ColgroupTag(); }
    public static ColgroupTag colgroup (String text)                                {  return new ColgroupTag().withText(text); }
    public static ColgroupTag colgroup (DomContent... dc)                           {  return new ColgroupTag().with(dc); }
    public static ColgroupTag colgroup (Attr.ShortForm shortAttr)                   {  return Attr.addTo( new ColgroupTag(), shortAttr); }
    public static ColgroupTag colgroup (Attr.ShortForm shortAttr, String text)      {  return Attr.addTo( new ColgroupTag().withText(text), shortAttr); }
    public static ColgroupTag colgroup (Attr.ShortForm shortAttr, DomContent... dc) {  return Attr.addTo( new ColgroupTag().with(dc), shortAttr); }

    public static DataTag data ()                                           {  return new DataTag(); }
    public static DataTag data (String text)                                {  return new DataTag().withText(text); }
    public static DataTag data (DomContent... dc)                           {  return new DataTag().with(dc); }
    public static DataTag data (Attr.ShortForm shortAttr)                   {  return Attr.addTo( new DataTag(), shortAttr); }
    public static DataTag data (Attr.ShortForm shortAttr, String text)      {  return Attr.addTo( new DataTag().withText(text), shortAttr); }
    public static DataTag data (Attr.ShortForm shortAttr, DomContent... dc) {  return Attr.addTo( new DataTag().with(dc), shortAttr); }

    public static DatalistTag datalist ()                                           {  return new DatalistTag(); }
    public static DatalistTag datalist (String text)                                {  return new DatalistTag().withText(text); }
    public static DatalistTag datalist (DomContent... dc)                           {  return new DatalistTag().with(dc); }
    public static DatalistTag datalist (Attr.ShortForm shortAttr)                   {  return Attr.addTo( new DatalistTag(), shortAttr); }
    public static DatalistTag datalist (Attr.ShortForm shortAttr, String text)      {  return Attr.addTo( new DatalistTag().withText(text), shortAttr); }
    public static DatalistTag datalist (Attr.ShortForm shortAttr, DomContent... dc) {  return Attr.addTo( new DatalistTag().with(dc), shortAttr); }

    public static DdTag dd ()                                                       {  return new DdTag(); }
    public static DdTag dd (String text)                                            {  return new DdTag().withText(text); }
    public static DdTag dd (DomContent... dc)                                       {  return new DdTag().with(dc); }
    public static DdTag dd (Attr.ShortForm shortAttr)                               {  return Attr.addTo( new DdTag(), shortAttr); }
    public static DdTag dd (Attr.ShortForm shortAttr, String text)                  {  return Attr.addTo( new DdTag().withText(text), shortAttr); }
    public static DdTag dd (Attr.ShortForm shortAttr, DomContent... dc)             {  return Attr.addTo( new DdTag().with(dc), shortAttr); }

    public static DelTag del ()                                                     {  return new DelTag(); }
    public static DelTag del (String text)                                          {  return new DelTag().withText(text); }
    public static DelTag del (DomContent... dc)                                     {  return new DelTag().with(dc); }
    public static DelTag del (Attr.ShortForm shortAttr)                             {  return Attr.addTo( new DelTag(), shortAttr); }
    public static DelTag del (Attr.ShortForm shortAttr, String text)                {  return Attr.addTo( new DelTag().withText(text), shortAttr); }
    public static DelTag del (Attr.ShortForm shortAttr, DomContent... dc)           {  return Attr.addTo( new DelTag().with(dc), shortAttr); }

    public static DetailsTag details ()                                             {  return new DetailsTag(); }
    public static DetailsTag details (String text)                                  {  return new DetailsTag().withText(text); }
    public static DetailsTag details (DomContent... dc)                             {  return new DetailsTag().with(dc); }
    public static DetailsTag details (Attr.ShortForm shortAttr)                     {  return Attr.addTo( new DetailsTag(), shortAttr); }
    public static DetailsTag details (Attr.ShortForm shortAttr, String text)        {  return Attr.addTo( new DetailsTag().withText(text), shortAttr); }
    public static DetailsTag details (Attr.ShortForm shortAttr, DomContent... dc)   {  return Attr.addTo( new DetailsTag().with(dc), shortAttr); }

    public static DfnTag dfn ()                                                     {  return new DfnTag(); }
    public static DfnTag dfn (String text)                                          {  return new DfnTag().withText(text); }
    public static DfnTag dfn (DomContent... dc)                                     {  return new DfnTag().with(dc); }
    public static DfnTag dfn (Attr.ShortForm shortAttr)                             {  return Attr.addTo( new DfnTag(), shortAttr); }
    public static DfnTag dfn (Attr.ShortForm shortAttr, String text)                {  return Attr.addTo( new DfnTag().withText(text), shortAttr); }
    public static DfnTag dfn (Attr.ShortForm shortAttr, DomContent... dc)           {  return Attr.addTo( new DfnTag().with(dc), shortAttr); }

    public static DialogTag dialog ()                                               {  return new DialogTag(); }
    public static DialogTag dialog (String text)                                    {  return new DialogTag().withText(text); }
    public static DialogTag dialog (DomContent... dc)                               {  return new DialogTag().with(dc); }
    public static DialogTag dialog (Attr.ShortForm shortAttr)                       {  return Attr.addTo( new DialogTag(), shortAttr); }
    public static DialogTag dialog (Attr.ShortForm shortAttr, String text)          {  return Attr.addTo( new DialogTag().withText(text), shortAttr); }
    public static DialogTag dialog (Attr.ShortForm shortAttr, DomContent... dc)     {  return Attr.addTo( new DialogTag().with(dc), shortAttr); }

    public static DivTag div ()                                                     {  return new DivTag(); }
    public static DivTag div (String text)                                          {  return new DivTag().withText(text); }
    public static DivTag div (DomContent... dc)                                     {  return new DivTag().with(dc); }
    public static DivTag div (Attr.ShortForm shortAttr)                             {  return Attr.addTo( new DivTag(), shortAttr); }
    public static DivTag div (Attr.ShortForm shortAttr, String text)                {  return Attr.addTo( new DivTag().withText(text), shortAttr); }
    public static DivTag div (Attr.ShortForm shortAttr, DomContent... dc)           {  return Attr.addTo( new DivTag().with(dc), shortAttr); }

    public static DlTag dl ()                                                       {  return new DlTag(); }
    public static DlTag dl (String text)                                            {  return new DlTag().withText(text); }
    public static DlTag dl (DomContent... dc)                                       {  return new DlTag().with(dc); }
    public static DlTag dl (Attr.ShortForm shortAttr)                               {  return Attr.addTo( new DlTag(), shortAttr); }
    public static DlTag dl (Attr.ShortForm shortAttr, String text)                  {  return Attr.addTo( new DlTag().withText(text), shortAttr); }
    public static DlTag dl (Attr.ShortForm shortAttr, DomContent... dc)             {  return Attr.addTo( new DlTag().with(dc), shortAttr); }

    public static DtTag dt ()                                                       {  return new DtTag(); }
    public static DtTag dt (String text)                                            {  return new DtTag().withText(text); }
    public static DtTag dt (DomContent... dc)                                       {  return new DtTag().with(dc); }
    public static DtTag dt (Attr.ShortForm shortAttr)                               {  return Attr.addTo( new DtTag(), shortAttr); }
    public static DtTag dt (Attr.ShortForm shortAttr, String text)                  {  return Attr.addTo( new DtTag().withText(text), shortAttr); }
    public static DtTag dt (Attr.ShortForm shortAttr, DomContent... dc)             {  return Attr.addTo( new DtTag().with(dc), shortAttr); }

    public static EmTag em ()                                                       {  return new EmTag(); }
    public static EmTag em (String text)                                            {  return new EmTag().withText(text); }
    public static EmTag em (DomContent... dc)                                       {  return new EmTag().with(dc); }
    public static EmTag em (Attr.ShortForm shortAttr)                               {  return Attr.addTo( new EmTag(), shortAttr); }
    public static EmTag em (Attr.ShortForm shortAttr, String text)                  {  return Attr.addTo( new EmTag().withText(text), shortAttr); }
    public static EmTag em (Attr.ShortForm shortAttr, DomContent... dc)             {  return Attr.addTo( new EmTag().with(dc), shortAttr); }

    public static FieldsetTag fieldset ()                                           {  return new FieldsetTag(); }
    public static FieldsetTag fieldset (String text)                                {  return new FieldsetTag().withText(text); }
    public static FieldsetTag fieldset (DomContent... dc)                           {  return new FieldsetTag().with(dc); }
    public static FieldsetTag fieldset (Attr.ShortForm shortAttr)                   {  return Attr.addTo( new FieldsetTag(), shortAttr); }
    public static FieldsetTag fieldset (Attr.ShortForm shortAttr, String text)      {  return Attr.addTo( new FieldsetTag().withText(text), shortAttr); }
    public static FieldsetTag fieldset (Attr.ShortForm shortAttr, DomContent... dc) {  return Attr.addTo( new FieldsetTag().with(dc), shortAttr); }

    public static FigcaptionTag figcaption ()                                       {  return new FigcaptionTag(); }
    public static FigcaptionTag figcaption (String text)                            {  return new FigcaptionTag().withText(text); }
    public static FigcaptionTag figcaption (DomContent... dc)                       {  return new FigcaptionTag().with(dc); }
    public static FigcaptionTag figcaption (Attr.ShortForm shortAttr)               {  return Attr.addTo( new FigcaptionTag(), shortAttr); }
    public static FigcaptionTag figcaption (Attr.ShortForm shortAttr, String text)  {  return Attr.addTo( new FigcaptionTag().withText(text), shortAttr); }
    public static FigcaptionTag figcaption (Attr.ShortForm shortAttr, DomContent... dc){  return Attr.addTo( new FigcaptionTag().with(dc), shortAttr); }

    public static FigureTag figure ()                                               {  return new FigureTag(); }
    public static FigureTag figure (String text)                                    {  return new FigureTag().withText(text); }
    public static FigureTag figure (DomContent... dc)                               {  return new FigureTag().with(dc); }
    public static FigureTag figure (Attr.ShortForm shortAttr)                       {  return Attr.addTo( new FigureTag(), shortAttr); }
    public static FigureTag figure (Attr.ShortForm shortAttr, String text)          {  return Attr.addTo( new FigureTag().withText(text), shortAttr); }
    public static FigureTag figure (Attr.ShortForm shortAttr, DomContent... dc)     {  return Attr.addTo( new FigureTag().with(dc), shortAttr); }

    public static FooterTag footer ()                                               {  return new FooterTag(); }
    public static FooterTag footer (String text)                                    {  return new FooterTag().withText(text); }
    public static FooterTag footer (DomContent... dc)                               {  return new FooterTag().with(dc); }
    public static FooterTag footer (Attr.ShortForm shortAttr)                       {  return Attr.addTo( new FooterTag(), shortAttr); }
    public static FooterTag footer (Attr.ShortForm shortAttr, String text)          {  return Attr.addTo( new FooterTag().withText(text), shortAttr); }
    public static FooterTag footer (Attr.ShortForm shortAttr, DomContent... dc)     {  return Attr.addTo( new FooterTag().with(dc), shortAttr); }

    public static FormTag form ()                                                   {  return new FormTag(); }
    public static FormTag form (String text)                                        {  return new FormTag().withText(text); }
    public static FormTag form (DomContent... dc)                                   {  return new FormTag().with(dc); }
    public static FormTag form (Attr.ShortForm shortAttr)                           {  return Attr.addTo( new FormTag(), shortAttr); }
    public static FormTag form (Attr.ShortForm shortAttr, String text)              {  return Attr.addTo( new FormTag().withText(text), shortAttr); }
    public static FormTag form (Attr.ShortForm shortAttr, DomContent... dc)         {  return Attr.addTo( new FormTag().with(dc), shortAttr); }

    public static H1Tag h1 ()                                                       {  return new H1Tag(); }
    public static H1Tag h1 (String text)                                            {  return new H1Tag().withText(text); }
    public static H1Tag h1 (DomContent... dc)                                       {  return new H1Tag().with(dc); }
    public static H1Tag h1 (Attr.ShortForm shortAttr)                               {  return Attr.addTo( new H1Tag(), shortAttr); }
    public static H1Tag h1 (Attr.ShortForm shortAttr, String text)                  {  return Attr.addTo( new H1Tag().withText(text), shortAttr); }
    public static H1Tag h1 (Attr.ShortForm shortAttr, DomContent... dc)             {  return Attr.addTo( new H1Tag().with(dc), shortAttr); }

    public static H2Tag h2 ()                                                       {  return new H2Tag(); }
    public static H2Tag h2 (String text)                                            {  return new H2Tag().withText(text); }
    public static H2Tag h2 (DomContent... dc)                                       {  return new H2Tag().with(dc); }
    public static H2Tag h2 (Attr.ShortForm shortAttr)                               {  return Attr.addTo( new H2Tag(), shortAttr); }
    public static H2Tag h2 (Attr.ShortForm shortAttr, String text)                  {  return Attr.addTo( new H2Tag().withText(text), shortAttr); }
    public static H2Tag h2 (Attr.ShortForm shortAttr, DomContent... dc)             {  return Attr.addTo( new H2Tag().with(dc), shortAttr); }

    public static H3Tag h3 ()                                                       {  return new H3Tag(); }
    public static H3Tag h3 (String text)                                            {  return new H3Tag().withText(text); }
    public static H3Tag h3 (DomContent... dc)                                       {  return new H3Tag().with(dc); }
    public static H3Tag h3 (Attr.ShortForm shortAttr)                               {  return Attr.addTo( new H3Tag(), shortAttr); }
    public static H3Tag h3 (Attr.ShortForm shortAttr, String text)                  {  return Attr.addTo( new H3Tag().withText(text), shortAttr); }
    public static H3Tag h3 (Attr.ShortForm shortAttr, DomContent... dc)             {  return Attr.addTo( new H3Tag().with(dc), shortAttr); }

    public static H4Tag h4 ()                                                       {  return new H4Tag(); }
    public static H4Tag h4 (String text)                                            {  return new H4Tag().withText(text); }
    public static H4Tag h4 (DomContent... dc)                                       {  return new H4Tag().with(dc); }
    public static H4Tag h4 (Attr.ShortForm shortAttr)                               {  return Attr.addTo( new H4Tag(), shortAttr); }
    public static H4Tag h4 (Attr.ShortForm shortAttr, String text)                  {  return Attr.addTo( new H4Tag().withText(text), shortAttr); }
    public static H4Tag h4 (Attr.ShortForm shortAttr, DomContent... dc)             {  return Attr.addTo( new H4Tag().with(dc), shortAttr); }

    public static H5Tag h5 ()                                                       {  return new H5Tag(); }
    public static H5Tag h5 (String text)                                            {  return new H5Tag().withText(text); }
    public static H5Tag h5 (DomContent... dc)                                       {  return new H5Tag().with(dc); }
    public static H5Tag h5 (Attr.ShortForm shortAttr)                               {  return Attr.addTo( new H5Tag(), shortAttr); }
    public static H5Tag h5 (Attr.ShortForm shortAttr, String text)                  {  return Attr.addTo( new H5Tag().withText(text), shortAttr); }
    public static H5Tag h5 (Attr.ShortForm shortAttr, DomContent... dc)             {  return Attr.addTo( new H5Tag().with(dc), shortAttr); }

    public static H6Tag h6 ()                                                       {  return new H6Tag(); }
    public static H6Tag h6 (String text)                                            {  return new H6Tag().withText(text); }
    public static H6Tag h6 (DomContent... dc)                                       {  return new H6Tag().with(dc); }
    public static H6Tag h6 (Attr.ShortForm shortAttr)                               {  return Attr.addTo( new H6Tag(), shortAttr); }
    public static H6Tag h6 (Attr.ShortForm shortAttr, String text)                  {  return Attr.addTo( new H6Tag().withText(text), shortAttr); }
    public static H6Tag h6 (Attr.ShortForm shortAttr, DomContent... dc)             {  return Attr.addTo( new H6Tag().with(dc), shortAttr); }

    public static HeaderTag header ()                                               {  return new HeaderTag(); }
    public static HeaderTag header (String text)                                    {  return new HeaderTag().withText(text); }
    public static HeaderTag header (DomContent... dc)                               {  return new HeaderTag().with(dc); }
    public static HeaderTag header (Attr.ShortForm shortAttr)                       {  return Attr.addTo( new HeaderTag(), shortAttr); }
    public static HeaderTag header (Attr.ShortForm shortAttr, String text)          {  return Attr.addTo( new HeaderTag().withText(text), shortAttr); }
    public static HeaderTag header (Attr.ShortForm shortAttr, DomContent... dc)     {  return Attr.addTo( new HeaderTag().with(dc), shortAttr); }

    public static ITag i ()                                                         {  return new ITag(); }
    public static ITag i (String text)                                              {  return new ITag().withText(text); }
    public static ITag i (DomContent... dc)                                         {  return new ITag().with(dc); }
    public static ITag i (Attr.ShortForm shortAttr)                                 {  return Attr.addTo( new ITag(), shortAttr); }
    public static ITag i (Attr.ShortForm shortAttr, String text)                    {  return Attr.addTo( new ITag().withText(text), shortAttr); }
    public static ITag i (Attr.ShortForm shortAttr, DomContent... dc)               {  return Attr.addTo( new ITag().with(dc), shortAttr); }

    public static IframeTag iframe ()                                               {  return new IframeTag(); }
    public static IframeTag iframe (String text)                                    {  return new IframeTag().withText(text); }
    public static IframeTag iframe (DomContent... dc)                               {  return new IframeTag().with(dc); }
    public static IframeTag iframe (Attr.ShortForm shortAttr)                       {  return Attr.addTo( new IframeTag(), shortAttr); }
    public static IframeTag iframe (Attr.ShortForm shortAttr, String text)          {  return Attr.addTo( new IframeTag().withText(text), shortAttr); }
    public static IframeTag iframe (Attr.ShortForm shortAttr, DomContent... dc)     {  return Attr.addTo( new IframeTag().with(dc), shortAttr); }

    public static InsTag ins ()                                                     {  return new InsTag(); }
    public static InsTag ins (String text)                                          {  return new InsTag().withText(text); }
    public static InsTag ins (DomContent... dc)                                     {  return new InsTag().with(dc); }
    public static InsTag ins (Attr.ShortForm shortAttr)                             {  return Attr.addTo( new InsTag(), shortAttr); }
    public static InsTag ins (Attr.ShortForm shortAttr, String text)                {  return Attr.addTo( new InsTag().withText(text), shortAttr); }
    public static InsTag ins (Attr.ShortForm shortAttr, DomContent... dc)           {  return Attr.addTo( new InsTag().with(dc), shortAttr); }

    public static KbdTag kbd ()                                                     {  return new KbdTag(); }
    public static KbdTag kbd (String text)                                          {  return new KbdTag().withText(text); }
    public static KbdTag kbd (DomContent... dc)                                     {  return new KbdTag().with(dc); }
    public static KbdTag kbd (Attr.ShortForm shortAttr)                             {  return Attr.addTo( new KbdTag(), shortAttr); }
    public static KbdTag kbd (Attr.ShortForm shortAttr, String text)                {  return Attr.addTo( new KbdTag().withText(text), shortAttr); }
    public static KbdTag kbd (Attr.ShortForm shortAttr, DomContent... dc)           {  return Attr.addTo( new KbdTag().with(dc), shortAttr); }

    public static LabelTag label ()                                                 {  return new LabelTag(); }
    public static LabelTag label (String text)                                      {  return new LabelTag().withText(text); }
    public static LabelTag label (DomContent... dc)                                 {  return new LabelTag().with(dc); }
    public static LabelTag label (Attr.ShortForm shortAttr)                         {  return Attr.addTo( new LabelTag(), shortAttr); }
    public static LabelTag label (Attr.ShortForm shortAttr, String text)            {  return Attr.addTo( new LabelTag().withText(text), shortAttr); }
    public static LabelTag label (Attr.ShortForm shortAttr, DomContent... dc)       {  return Attr.addTo( new LabelTag().with(dc), shortAttr); }

    public static LegendTag legend ()                                               {  return new LegendTag(); }
    public static LegendTag legend (String text)                                    {  return new LegendTag().withText(text); }
    public static LegendTag legend (DomContent... dc)                               {  return new LegendTag().with(dc); }
    public static LegendTag legend (Attr.ShortForm shortAttr)                       {  return Attr.addTo( new LegendTag(), shortAttr); }
    public static LegendTag legend (Attr.ShortForm shortAttr, String text)          {  return Attr.addTo( new LegendTag().withText(text), shortAttr); }
    public static LegendTag legend (Attr.ShortForm shortAttr, DomContent... dc)     {  return Attr.addTo( new LegendTag().with(dc), shortAttr); }

    public static LiTag li ()                                                       {  return new LiTag(); }
    public static LiTag li (String text)                                            {  return new LiTag().withText(text); }
    public static LiTag li (DomContent... dc)                                       {  return new LiTag().with(dc); }
    public static LiTag li (Attr.ShortForm shortAttr)                               {  return Attr.addTo( new LiTag(), shortAttr); }
    public static LiTag li (Attr.ShortForm shortAttr, String text)                  {  return Attr.addTo( new LiTag().withText(text), shortAttr); }
    public static LiTag li (Attr.ShortForm shortAttr, DomContent... dc)             {  return Attr.addTo( new LiTag().with(dc), shortAttr); }

    public static MainTag main ()                                                   {  return new MainTag(); }
    public static MainTag main (String text)                                        {  return new MainTag().withText(text); }
    public static MainTag main (DomContent... dc)                                   {  return new MainTag().with(dc); }
    public static MainTag main (Attr.ShortForm shortAttr)                           {  return Attr.addTo( new MainTag(), shortAttr); }
    public static MainTag main (Attr.ShortForm shortAttr, String text)              {  return Attr.addTo( new MainTag().withText(text), shortAttr); }
    public static MainTag main (Attr.ShortForm shortAttr, DomContent... dc)         {  return Attr.addTo( new MainTag().with(dc), shortAttr); }

    public static MapTag map ()                                                     {  return new MapTag(); }
    public static MapTag map (String text)                                          {  return new MapTag().withText(text); }
    public static MapTag map (DomContent... dc)                                     {  return new MapTag().with(dc); }
    public static MapTag map (Attr.ShortForm shortAttr)                             {  return Attr.addTo( new MapTag(), shortAttr); }
    public static MapTag map (Attr.ShortForm shortAttr, String text)                {  return Attr.addTo( new MapTag().withText(text), shortAttr); }
    public static MapTag map (Attr.ShortForm shortAttr, DomContent... dc)           {  return Attr.addTo( new MapTag().with(dc), shortAttr); }

    public static MarkTag mark ()                                                   {  return new MarkTag(); }
    public static MarkTag mark (String text)                                        {  return new MarkTag().withText(text); }
    public static MarkTag mark (DomContent... dc)                                   {  return new MarkTag().with(dc); }
    public static MarkTag mark (Attr.ShortForm shortAttr)                           {  return Attr.addTo( new MarkTag(), shortAttr); }
    public static MarkTag mark (Attr.ShortForm shortAttr, String text)              {  return Attr.addTo( new MarkTag().withText(text), shortAttr); }
    public static MarkTag mark (Attr.ShortForm shortAttr, DomContent... dc)         {  return Attr.addTo( new MarkTag().with(dc), shortAttr); }

    public static MenuTag menu ()                                                   {  return new MenuTag(); }
    public static MenuTag menu (String text)                                        {  return new MenuTag().withText(text); }
    public static MenuTag menu (DomContent... dc)                                   {  return new MenuTag().with(dc); }
    public static MenuTag menu (Attr.ShortForm shortAttr)                           {  return Attr.addTo( new MenuTag(), shortAttr); }
    public static MenuTag menu (Attr.ShortForm shortAttr, String text)              {  return Attr.addTo( new MenuTag().withText(text), shortAttr); }
    public static MenuTag menu (Attr.ShortForm shortAttr, DomContent... dc)         {  return Attr.addTo( new MenuTag().with(dc), shortAttr); }

    public static MenuitemTag menuitem ()                                           {  return new MenuitemTag(); }
    public static MenuitemTag menuitem (String text)                                {  return new MenuitemTag().withText(text); }
    public static MenuitemTag menuitem (DomContent... dc)                           {  return new MenuitemTag().with(dc); }
    public static MenuitemTag menuitem (Attr.ShortForm shortAttr)                   {  return Attr.addTo( new MenuitemTag(), shortAttr); }
    public static MenuitemTag menuitem (Attr.ShortForm shortAttr, String text)      {  return Attr.addTo( new MenuitemTag().withText(text), shortAttr); }
    public static MenuitemTag menuitem (Attr.ShortForm shortAttr, DomContent... dc) {  return Attr.addTo( new MenuitemTag().with(dc), shortAttr); }

    public static MeterTag meter ()                                                 {  return new MeterTag(); }
    public static MeterTag meter (String text)                                      {  return new MeterTag().withText(text); }
    public static MeterTag meter (DomContent... dc)                                 {  return new MeterTag().with(dc); }
    public static MeterTag meter (Attr.ShortForm shortAttr)                         {  return Attr.addTo( new MeterTag(), shortAttr); }
    public static MeterTag meter (Attr.ShortForm shortAttr, String text)            {  return Attr.addTo( new MeterTag().withText(text), shortAttr); }
    public static MeterTag meter (Attr.ShortForm shortAttr, DomContent... dc)       {  return Attr.addTo( new MeterTag().with(dc), shortAttr); }

    public static NavTag nav ()                                                     {  return new NavTag(); }
    public static NavTag nav (String text)                                          {  return new NavTag().withText(text); }
    public static NavTag nav (DomContent... dc)                                     {  return new NavTag().with(dc); }
    public static NavTag nav (Attr.ShortForm shortAttr)                             {  return Attr.addTo( new NavTag(), shortAttr); }
    public static NavTag nav (Attr.ShortForm shortAttr, String text)                {  return Attr.addTo( new NavTag().withText(text), shortAttr); }
    public static NavTag nav (Attr.ShortForm shortAttr, DomContent... dc)           {  return Attr.addTo( new NavTag().with(dc), shortAttr); }

    public static NoscriptTag noscript ()                                           {  return new NoscriptTag(); }
    public static NoscriptTag noscript (String text)                                {  return new NoscriptTag().withText(text); }
    public static NoscriptTag noscript (DomContent... dc)                           {  return new NoscriptTag().with(dc); }
    public static NoscriptTag noscript (Attr.ShortForm shortAttr)                   {  return Attr.addTo( new NoscriptTag(), shortAttr); }
    public static NoscriptTag noscript (Attr.ShortForm shortAttr, String text)      {  return Attr.addTo( new NoscriptTag().withText(text), shortAttr); }
    public static NoscriptTag noscript (Attr.ShortForm shortAttr, DomContent... dc) {  return Attr.addTo( new NoscriptTag().with(dc), shortAttr); }

    public static ObjectTag object ()                                               {  return new ObjectTag(); }
    public static ObjectTag object (String text)                                    {  return new ObjectTag().withText(text); }
    public static ObjectTag object (DomContent... dc)                               {  return new ObjectTag().with(dc); }
    public static ObjectTag object (Attr.ShortForm shortAttr)                       {  return Attr.addTo( new ObjectTag(), shortAttr); }
    public static ObjectTag object (Attr.ShortForm shortAttr, String text)          {  return Attr.addTo( new ObjectTag().withText(text), shortAttr); }
    public static ObjectTag object (Attr.ShortForm shortAttr, DomContent... dc)     {  return Attr.addTo( new ObjectTag().with(dc), shortAttr); }

    public static OlTag ol ()                                                       {  return new OlTag(); }
    public static OlTag ol (String text)                                            {  return new OlTag().withText(text); }
    public static OlTag ol (DomContent... dc)                                       {  return new OlTag().with(dc); }
    public static OlTag ol (Attr.ShortForm shortAttr)                               {  return Attr.addTo( new OlTag(), shortAttr); }
    public static OlTag ol (Attr.ShortForm shortAttr, String text)                  {  return Attr.addTo( new OlTag().withText(text), shortAttr); }
    public static OlTag ol (Attr.ShortForm shortAttr, DomContent... dc)             {  return Attr.addTo( new OlTag().with(dc), shortAttr); }

    public static OptgroupTag optgroup ()                                           {  return new OptgroupTag(); }
    public static OptgroupTag optgroup (String text)                                {  return new OptgroupTag().withText(text); }
    public static OptgroupTag optgroup (DomContent... dc)                           {  return new OptgroupTag().with(dc); }
    public static OptgroupTag optgroup (Attr.ShortForm shortAttr)                   {  return Attr.addTo( new OptgroupTag(), shortAttr); }
    public static OptgroupTag optgroup (Attr.ShortForm shortAttr, String text)      {  return Attr.addTo( new OptgroupTag().withText(text), shortAttr); }
    public static OptgroupTag optgroup (Attr.ShortForm shortAttr, DomContent... dc) {  return Attr.addTo( new OptgroupTag().with(dc), shortAttr); }

    public static OptionTag option ()                                               {  return new OptionTag(); }
    public static OptionTag option (String text)                                    {  return new OptionTag().withText(text); }
    public static OptionTag option (DomContent... dc)                               {  return new OptionTag().with(dc); }
    public static OptionTag option (Attr.ShortForm shortAttr)                       {  return Attr.addTo( new OptionTag(), shortAttr); }
    public static OptionTag option (Attr.ShortForm shortAttr, String text)          {  return Attr.addTo( new OptionTag().withText(text), shortAttr); }
    public static OptionTag option (Attr.ShortForm shortAttr, DomContent... dc)     {  return Attr.addTo( new OptionTag().with(dc), shortAttr); }

    public static OutputTag output ()                                               {  return new OutputTag(); }
    public static OutputTag output (String text)                                    {  return new OutputTag().withText(text); }
    public static OutputTag output (DomContent... dc)                               {  return new OutputTag().with(dc); }
    public static OutputTag output (Attr.ShortForm shortAttr)                       {  return Attr.addTo( new OutputTag(), shortAttr); }
    public static OutputTag output (Attr.ShortForm shortAttr, String text)          {  return Attr.addTo( new OutputTag().withText(text), shortAttr); }
    public static OutputTag output (Attr.ShortForm shortAttr, DomContent... dc)     {  return Attr.addTo( new OutputTag().with(dc), shortAttr); }

    public static PTag p ()                                                         {  return new PTag(); }
    public static PTag p (String text)                                              {  return new PTag().withText(text); }
    public static PTag p (DomContent... dc)                                         {  return new PTag().with(dc); }
    public static PTag p (Attr.ShortForm shortAttr)                                 {  return Attr.addTo( new PTag(), shortAttr); }
    public static PTag p (Attr.ShortForm shortAttr, String text)                    {  return Attr.addTo( new PTag().withText(text), shortAttr); }
    public static PTag p (Attr.ShortForm shortAttr, DomContent... dc)               {  return Attr.addTo( new PTag().with(dc), shortAttr); }

    public static PictureTag picture ()                                             {  return new PictureTag(); }
    public static PictureTag picture (String text)                                  {  return new PictureTag().withText(text); }
    public static PictureTag picture (DomContent... dc)                             {  return new PictureTag().with(dc); }
    public static PictureTag picture (Attr.ShortForm shortAttr)                     {  return Attr.addTo( new PictureTag(), shortAttr); }
    public static PictureTag picture (Attr.ShortForm shortAttr, String text)        {  return Attr.addTo( new PictureTag().withText(text), shortAttr); }
    public static PictureTag picture (Attr.ShortForm shortAttr, DomContent... dc)   {  return Attr.addTo( new PictureTag().with(dc), shortAttr); }

    public static PreTag pre ()                                                     {  return new PreTag(); }
    public static PreTag pre (String text)                                          {  return new PreTag().withText(text); }
    public static PreTag pre (DomContent... dc)                                     {  return new PreTag().with(dc); }
    public static PreTag pre (Attr.ShortForm shortAttr)                             {  return Attr.addTo( new PreTag(), shortAttr); }
    public static PreTag pre (Attr.ShortForm shortAttr, String text)                {  return Attr.addTo( new PreTag().withText(text), shortAttr); }
    public static PreTag pre (Attr.ShortForm shortAttr, DomContent... dc)           {  return Attr.addTo( new PreTag().with(dc), shortAttr); }

    public static ProgressTag progress ()                                           {  return new ProgressTag(); }
    public static ProgressTag progress (String text)                                {  return new ProgressTag().withText(text); }
    public static ProgressTag progress (DomContent... dc)                           {  return new ProgressTag().with(dc); }
    public static ProgressTag progress (Attr.ShortForm shortAttr)                   {  return Attr.addTo( new ProgressTag(), shortAttr); }
    public static ProgressTag progress (Attr.ShortForm shortAttr, String text)      {  return Attr.addTo( new ProgressTag().withText(text), shortAttr); }
    public static ProgressTag progress (Attr.ShortForm shortAttr, DomContent... dc) {  return Attr.addTo( new ProgressTag().with(dc), shortAttr); }

    public static QTag q ()                                                         {  return new QTag(); }
    public static QTag q (String text)                                              {  return new QTag().withText(text); }
    public static QTag q (DomContent... dc)                                         {  return new QTag().with(dc); }
    public static QTag q (Attr.ShortForm shortAttr)                                 {  return Attr.addTo( new QTag(), shortAttr); }
    public static QTag q (Attr.ShortForm shortAttr, String text)                    {  return Attr.addTo( new QTag().withText(text), shortAttr); }
    public static QTag q (Attr.ShortForm shortAttr, DomContent... dc)               {  return Attr.addTo( new QTag().with(dc), shortAttr); }

    public static RpTag rp ()                                                       {  return new RpTag(); }
    public static RpTag rp (String text)                                            {  return new RpTag().withText(text); }
    public static RpTag rp (DomContent... dc)                                       {  return new RpTag().with(dc); }
    public static RpTag rp (Attr.ShortForm shortAttr)                               {  return Attr.addTo( new RpTag(), shortAttr); }
    public static RpTag rp (Attr.ShortForm shortAttr, String text)                  {  return Attr.addTo( new RpTag().withText(text), shortAttr); }
    public static RpTag rp (Attr.ShortForm shortAttr, DomContent... dc)             {  return Attr.addTo( new RpTag().with(dc), shortAttr); }

    public static RtTag rt ()                                                       {  return new RtTag(); }
    public static RtTag rt (String text)                                            {  return new RtTag().withText(text); }
    public static RtTag rt (DomContent... dc)                                       {  return new RtTag().with(dc); }
    public static RtTag rt (Attr.ShortForm shortAttr)                               {  return Attr.addTo( new RtTag(), shortAttr); }
    public static RtTag rt (Attr.ShortForm shortAttr, String text)                  {  return Attr.addTo( new RtTag().withText(text), shortAttr); }
    public static RtTag rt (Attr.ShortForm shortAttr, DomContent... dc)             {  return Attr.addTo( new RtTag().with(dc), shortAttr); }

    public static RubyTag ruby ()                                                   {  return new RubyTag(); }
    public static RubyTag ruby (String text)                                        {  return new RubyTag().withText(text); }
    public static RubyTag ruby (DomContent... dc)                                   {  return new RubyTag().with(dc); }
    public static RubyTag ruby (Attr.ShortForm shortAttr)                           {  return Attr.addTo( new RubyTag(), shortAttr); }
    public static RubyTag ruby (Attr.ShortForm shortAttr, String text)              {  return Attr.addTo( new RubyTag().withText(text), shortAttr); }
    public static RubyTag ruby (Attr.ShortForm shortAttr, DomContent... dc)         {  return Attr.addTo( new RubyTag().with(dc), shortAttr); }

    public static STag s ()                                                         {  return new STag(); }
    public static STag s (String text)                                              {  return new STag().withText(text); }
    public static STag s (DomContent... dc)                                         {  return new STag().with(dc); }
    public static STag s (Attr.ShortForm shortAttr)                                 {  return Attr.addTo( new STag(), shortAttr); }
    public static STag s (Attr.ShortForm shortAttr, String text)                    {  return Attr.addTo( new STag().withText(text), shortAttr); }
    public static STag s (Attr.ShortForm shortAttr, DomContent... dc)               {  return Attr.addTo( new STag().with(dc), shortAttr); }

    public static SampTag samp ()                                                   {  return new SampTag(); }
    public static SampTag samp (String text)                                        {  return new SampTag().withText(text); }
    public static SampTag samp (DomContent... dc)                                   {  return new SampTag().with(dc); }
    public static SampTag samp (Attr.ShortForm shortAttr)                           {  return Attr.addTo( new SampTag(), shortAttr); }
    public static SampTag samp (Attr.ShortForm shortAttr, String text)              {  return Attr.addTo( new SampTag().withText(text), shortAttr); }
    public static SampTag samp (Attr.ShortForm shortAttr, DomContent... dc)         {  return Attr.addTo( new SampTag().with(dc), shortAttr); }

    public static ScriptTag script ()                                               {  return new ScriptTag(); }
    public static ScriptTag script (String text)                                    {  return new ScriptTag().with(new UnescapedText(text)); }
    public static ScriptTag script (DomContent... dc)                               {  return new ScriptTag().with(dc); }
    public static ScriptTag script (Attr.ShortForm shortAttr)                       {  return Attr.addTo( new ScriptTag(), shortAttr); }
    public static ScriptTag script (Attr.ShortForm shortAttr, String text)          {  return Attr.addTo( new ScriptTag().with(new UnescapedText(text)), shortAttr); }
    public static ScriptTag script (Attr.ShortForm shortAttr, DomContent... dc)     {  return Attr.addTo( new ScriptTag().with(dc), shortAttr); }

    public static SectionTag section ()                                             {  return new SectionTag(); }
    public static SectionTag section (String text)                                  {  return new SectionTag().withText(text); }
    public static SectionTag section (DomContent... dc)                             {  return new SectionTag().with(dc); }
    public static SectionTag section (Attr.ShortForm shortAttr)                     {  return Attr.addTo( new SectionTag(), shortAttr); }
    public static SectionTag section (Attr.ShortForm shortAttr, String text)        {  return Attr.addTo( new SectionTag().withText(text), shortAttr); }
    public static SectionTag section (Attr.ShortForm shortAttr, DomContent... dc)   {  return Attr.addTo( new SectionTag().with(dc), shortAttr); }

    public static SelectTag select ()                                               {  return new SelectTag(); }
    public static SelectTag select (String text)                                    {  return new SelectTag().withText(text); }
    public static SelectTag select (DomContent... dc)                               {  return new SelectTag().with(dc); }
    public static SelectTag select (Attr.ShortForm shortAttr)                       {  return Attr.addTo( new SelectTag(), shortAttr); }
    public static SelectTag select (Attr.ShortForm shortAttr, String text)          {  return Attr.addTo( new SelectTag().withText(text), shortAttr); }
    public static SelectTag select (Attr.ShortForm shortAttr, DomContent... dc)     {  return Attr.addTo( new SelectTag().with(dc), shortAttr); }

    public static SlotTag slot ()                                                 {  return new SlotTag(); }
    public static SlotTag slot (String text)                                      {  return new SlotTag().withText(text); }
    public static SlotTag slot (DomContent... dc)                                 {  return new SlotTag().with(dc); }
    public static SlotTag slot (Attr.ShortForm shortAttr)                         {  return Attr.addTo( new SlotTag(), shortAttr); }
    public static SlotTag slot (Attr.ShortForm shortAttr, String text)            {  return Attr.addTo( new SlotTag().withText(text), shortAttr); }
    public static SlotTag slot (Attr.ShortForm shortAttr, DomContent... dc)       {  return Attr.addTo( new SlotTag().with(dc), shortAttr); }

    public static SmallTag small ()                                                 {  return new SmallTag(); }
    public static SmallTag small (String text)                                      {  return new SmallTag().withText(text); }
    public static SmallTag small (DomContent... dc)                                 {  return new SmallTag().with(dc); }
    public static SmallTag small (Attr.ShortForm shortAttr)                         {  return Attr.addTo( new SmallTag(), shortAttr); }
    public static SmallTag small (Attr.ShortForm shortAttr, String text)            {  return Attr.addTo( new SmallTag().withText(text), shortAttr); }
    public static SmallTag small (Attr.ShortForm shortAttr, DomContent... dc)       {  return Attr.addTo( new SmallTag().with(dc), shortAttr); }

    public static SpanTag span ()                                                   {  return new SpanTag(); }
    public static SpanTag span (String text)                                        {  return new SpanTag().withText(text); }
    public static SpanTag span (DomContent... dc)                                   {  return new SpanTag().with(dc); }
    public static SpanTag span (Attr.ShortForm shortAttr)                           {  return Attr.addTo( new SpanTag(), shortAttr); }
    public static SpanTag span (Attr.ShortForm shortAttr, String text)              {  return Attr.addTo( new SpanTag().withText(text), shortAttr); }
    public static SpanTag span (Attr.ShortForm shortAttr, DomContent... dc)         {  return Attr.addTo( new SpanTag().with(dc), shortAttr); }

    public static StrongTag strong ()                                               {  return new StrongTag(); }
    public static StrongTag strong (String text)                                    {  return new StrongTag().withText(text); }
    public static StrongTag strong (DomContent... dc)                               {  return new StrongTag().with(dc); }
    public static StrongTag strong (Attr.ShortForm shortAttr)                       {  return Attr.addTo( new StrongTag(), shortAttr); }
    public static StrongTag strong (Attr.ShortForm shortAttr, String text)          {  return Attr.addTo( new StrongTag().withText(text), shortAttr); }
    public static StrongTag strong (Attr.ShortForm shortAttr, DomContent... dc)     {  return Attr.addTo( new StrongTag().with(dc), shortAttr); }

    public static StyleTag style ()                                                 {  return new StyleTag(); }
    public static StyleTag style (String text)                                      {  return new StyleTag().with(new UnescapedText(text)); }
    public static StyleTag style (DomContent... dc)                                 {  return new StyleTag().with(dc); }
    public static StyleTag style (Attr.ShortForm shortAttr)                         {  return Attr.addTo( new StyleTag(), shortAttr); }
    public static StyleTag style (Attr.ShortForm shortAttr, String text)            {  return Attr.addTo( new StyleTag().with(new UnescapedText(text)), shortAttr); }
    public static StyleTag style (Attr.ShortForm shortAttr, DomContent... dc)       {  return Attr.addTo( new StyleTag().with(dc), shortAttr); }

    public static SubTag sub ()                                                     {  return new SubTag(); }
    public static SubTag sub (String text)                                          {  return new SubTag().withText(text); }
    public static SubTag sub (DomContent... dc)                                     {  return new SubTag().with(dc); }
    public static SubTag sub (Attr.ShortForm shortAttr)                             {  return Attr.addTo( new SubTag(), shortAttr); }
    public static SubTag sub (Attr.ShortForm shortAttr, String text)                {  return Attr.addTo( new SubTag().withText(text), shortAttr); }
    public static SubTag sub (Attr.ShortForm shortAttr, DomContent... dc)           {  return Attr.addTo( new SubTag().with(dc), shortAttr); }

    public static SummaryTag summary ()                                             {  return new SummaryTag(); }
    public static SummaryTag summary (String text)                                  {  return new SummaryTag().withText(text); }
    public static SummaryTag summary (DomContent... dc)                             {  return new SummaryTag().with(dc); }
    public static SummaryTag summary (Attr.ShortForm shortAttr)                     {  return Attr.addTo( new SummaryTag(), shortAttr); }
    public static SummaryTag summary (Attr.ShortForm shortAttr, String text)        {  return Attr.addTo( new SummaryTag().withText(text), shortAttr); }
    public static SummaryTag summary (Attr.ShortForm shortAttr, DomContent... dc)   {  return Attr.addTo( new SummaryTag().with(dc), shortAttr); }

    public static SupTag sup ()                                                     {  return new SupTag(); }
    public static SupTag sup (String text)                                          {  return new SupTag().withText(text); }
    public static SupTag sup (DomContent... dc)                                     {  return new SupTag().with(dc); }
    public static SupTag sup (Attr.ShortForm shortAttr)                             {  return Attr.addTo( new SupTag(), shortAttr); }
    public static SupTag sup (Attr.ShortForm shortAttr, String text)                {  return Attr.addTo( new SupTag().withText(text), shortAttr); }
    public static SupTag sup (Attr.ShortForm shortAttr, DomContent... dc)           {  return Attr.addTo( new SupTag().with(dc), shortAttr); }

    public static TableTag table ()                                                 {  return new TableTag(); }
    public static TableTag table (String text)                                      {  return new TableTag().withText(text); }
    public static TableTag table (DomContent... dc)                                 {  return new TableTag().with(dc); }
    public static TableTag table (Attr.ShortForm shortAttr)                         {  return Attr.addTo( new TableTag(), shortAttr); }
    public static TableTag table (Attr.ShortForm shortAttr, String text)            {  return Attr.addTo( new TableTag().withText(text), shortAttr); }
    public static TableTag table (Attr.ShortForm shortAttr, DomContent... dc)       {  return Attr.addTo( new TableTag().with(dc), shortAttr); }

    public static TbodyTag tbody ()                                                 {  return new TbodyTag(); }
    public static TbodyTag tbody (String text)                                      {  return new TbodyTag().withText(text); }
    public static TbodyTag tbody (DomContent... dc)                                 {  return new TbodyTag().with(dc); }
    public static TbodyTag tbody (Attr.ShortForm shortAttr)                         {  return Attr.addTo( new TbodyTag(), shortAttr); }
    public static TbodyTag tbody (Attr.ShortForm shortAttr, String text)            {  return Attr.addTo( new TbodyTag().withText(text), shortAttr); }
    public static TbodyTag tbody (Attr.ShortForm shortAttr, DomContent... dc)       {  return Attr.addTo( new TbodyTag().with(dc), shortAttr); }

    public static TdTag td ()                                                       {  return new TdTag(); }
    public static TdTag td (String text)                                            {  return new TdTag().withText(text); }
    public static TdTag td (DomContent... dc)                                       {  return new TdTag().with(dc); }
    public static TdTag td (Attr.ShortForm shortAttr)                               {  return Attr.addTo( new TdTag(), shortAttr); }
    public static TdTag td (Attr.ShortForm shortAttr, String text)                  {  return Attr.addTo( new TdTag().withText(text), shortAttr); }
    public static TdTag td (Attr.ShortForm shortAttr, DomContent... dc)             {  return Attr.addTo( new TdTag().with(dc), shortAttr); }

    public static TemplateTag template ()                                           {  return new TemplateTag(); }
    public static TemplateTag template (String text)                                {  return new TemplateTag().withText(text); }
    public static TemplateTag template (DomContent... dc)                           {  return new TemplateTag().with(dc); }
    public static TemplateTag template (Attr.ShortForm shortAttr)                   {  return Attr.addTo( new TemplateTag(), shortAttr); }
    public static TemplateTag template (Attr.ShortForm shortAttr, String text)      {  return Attr.addTo( new TemplateTag().withText(text), shortAttr); }
    public static TemplateTag template (Attr.ShortForm shortAttr, DomContent... dc) {  return Attr.addTo( new TemplateTag().with(dc), shortAttr); }

    public static TextareaTag textarea ()                                           {  return new TextareaTag(); }
    public static TextareaTag textarea (String text)                                {  return new TextareaTag().withText(text); }
    public static TextareaTag textarea (DomContent... dc)                           {  return new TextareaTag().with(dc); }
    public static TextareaTag textarea (Attr.ShortForm shortAttr)                   {  return Attr.addTo( new TextareaTag(), shortAttr); }
    public static TextareaTag textarea (Attr.ShortForm shortAttr, String text)      {  return Attr.addTo( new TextareaTag().withText(text), shortAttr); }
    public static TextareaTag textarea (Attr.ShortForm shortAttr, DomContent... dc) {  return Attr.addTo( new TextareaTag().with(dc), shortAttr); }

    public static TfootTag tfoot ()                                                 {  return new TfootTag(); }
    public static TfootTag tfoot (String text)                                      {  return new TfootTag().withText(text); }
    public static TfootTag tfoot (DomContent... dc)                                 {  return new TfootTag().with(dc); }
    public static TfootTag tfoot (Attr.ShortForm shortAttr)                         {  return Attr.addTo( new TfootTag(), shortAttr); }
    public static TfootTag tfoot (Attr.ShortForm shortAttr, String text)            {  return Attr.addTo( new TfootTag().withText(text), shortAttr); }
    public static TfootTag tfoot (Attr.ShortForm shortAttr, DomContent... dc)       {  return Attr.addTo( new TfootTag().with(dc), shortAttr); }

    public static ThTag th ()                                                       {  return new ThTag(); }
    public static ThTag th (String text)                                            {  return new ThTag().withText(text); }
    public static ThTag th (DomContent... dc)                                       {  return new ThTag().with(dc); }
    public static ThTag th (Attr.ShortForm shortAttr)                               {  return Attr.addTo( new ThTag(), shortAttr); }
    public static ThTag th (Attr.ShortForm shortAttr, String text)                  {  return Attr.addTo( new ThTag().withText(text), shortAttr); }
    public static ThTag th (Attr.ShortForm shortAttr, DomContent... dc)             {  return Attr.addTo( new ThTag().with(dc), shortAttr); }

    public static TheadTag thead ()                                                 {  return new TheadTag(); }
    public static TheadTag thead (String text)                                      {  return new TheadTag().withText(text); }
    public static TheadTag thead (DomContent... dc)                                 {  return new TheadTag().with(dc); }
    public static TheadTag thead (Attr.ShortForm shortAttr)                         {  return Attr.addTo( new TheadTag(), shortAttr); }
    public static TheadTag thead (Attr.ShortForm shortAttr, String text)            {  return Attr.addTo( new TheadTag().withText(text), shortAttr); }
    public static TheadTag thead (Attr.ShortForm shortAttr, DomContent... dc)       {  return Attr.addTo( new TheadTag().with(dc), shortAttr); }

    public static TimeTag time ()                                                   {  return new TimeTag(); }
    public static TimeTag time (String text)                                        {  return new TimeTag().withText(text); }
    public static TimeTag time (DomContent... dc)                                   {  return new TimeTag().with(dc); }
    public static TimeTag time (Attr.ShortForm shortAttr)                           {  return Attr.addTo( new TimeTag(), shortAttr); }
    public static TimeTag time (Attr.ShortForm shortAttr, String text)              {  return Attr.addTo( new TimeTag().withText(text), shortAttr); }
    public static TimeTag time (Attr.ShortForm shortAttr, DomContent... dc)         {  return Attr.addTo( new TimeTag().with(dc), shortAttr); }

    public static TitleTag title ()                                                 {  return new TitleTag(); }
    public static TitleTag title (String text)                                      {  return new TitleTag().withText(text); }
    public static TitleTag title (DomContent... dc)                                 {  return new TitleTag().with(dc); }
    public static TitleTag title (Attr.ShortForm shortAttr)                         {  return Attr.addTo( new TitleTag(), shortAttr); }
    public static TitleTag title (Attr.ShortForm shortAttr, String text)            {  return Attr.addTo( new TitleTag().withText(text), shortAttr); }
    public static TitleTag title (Attr.ShortForm shortAttr, DomContent... dc)       {  return Attr.addTo( new TitleTag().with(dc), shortAttr); }

    public static TrTag tr ()                                                       {  return new TrTag(); }
    public static TrTag tr (String text)                                            {  return new TrTag().withText(text); }
    public static TrTag tr (DomContent... dc)                                       {  return new TrTag().with(dc); }
    public static TrTag tr (Attr.ShortForm shortAttr)                               {  return Attr.addTo( new TrTag(), shortAttr); }
    public static TrTag tr (Attr.ShortForm shortAttr, String text)                  {  return Attr.addTo( new TrTag().withText(text), shortAttr); }
    public static TrTag tr (Attr.ShortForm shortAttr, DomContent... dc)             {  return Attr.addTo( new TrTag().with(dc), shortAttr); }

    public static UTag u ()                                                         {  return new UTag(); }
    public static UTag u (String text)                                              {  return new UTag().withText(text); }
    public static UTag u (DomContent... dc)                                         {  return new UTag().with(dc); }
    public static UTag u (Attr.ShortForm shortAttr)                                 {  return Attr.addTo( new UTag(), shortAttr); }
    public static UTag u (Attr.ShortForm shortAttr, String text)                    {  return Attr.addTo( new UTag().withText(text), shortAttr); }
    public static UTag u (Attr.ShortForm shortAttr, DomContent... dc)               {  return Attr.addTo( new UTag().with(dc), shortAttr); }

    public static UlTag ul ()                                                       {  return new UlTag(); }
    public static UlTag ul (String text)                                            {  return new UlTag().withText(text); }
    public static UlTag ul (DomContent... dc)                                       {  return new UlTag().with(dc); }
    public static UlTag ul (Attr.ShortForm shortAttr)                               {  return Attr.addTo( new UlTag(), shortAttr); }
    public static UlTag ul (Attr.ShortForm shortAttr, String text)                  {  return Attr.addTo( new UlTag().withText(text), shortAttr); }
    public static UlTag ul (Attr.ShortForm shortAttr, DomContent... dc)             {  return Attr.addTo( new UlTag().with(dc), shortAttr); }

    public static VarTag var ()                                                     {  return new VarTag(); }
    public static VarTag var (String text)                                          {  return new VarTag().withText(text); }
    public static VarTag var (DomContent... dc)                                     {  return new VarTag().with(dc); }
    public static VarTag var (Attr.ShortForm shortAttr)                             {  return Attr.addTo( new VarTag(), shortAttr); }
    public static VarTag var (Attr.ShortForm shortAttr, String text)                {  return Attr.addTo( new VarTag().withText(text), shortAttr); }
    public static VarTag var (Attr.ShortForm shortAttr, DomContent... dc)           {  return Attr.addTo( new VarTag().with(dc), shortAttr); }

    public static VideoTag video ()                                                 {  return new VideoTag(); }
    public static VideoTag video (String text)                                      {  return new VideoTag().withText(text); }
    public static VideoTag video (DomContent... dc)                                 {  return new VideoTag().with(dc); }
    public static VideoTag video (Attr.ShortForm shortAttr)                         {  return Attr.addTo( new VideoTag(), shortAttr); }
    public static VideoTag video (Attr.ShortForm shortAttr, String text)            {  return Attr.addTo( new VideoTag().withText(text), shortAttr); }
    public static VideoTag video (Attr.ShortForm shortAttr, DomContent... dc)       {  return Attr.addTo( new VideoTag().with(dc), shortAttr); }

}
