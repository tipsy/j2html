package javaHtmlGenerator.tags;


public class TagCreator {

    //SIMPLE TAGS
    //TODO: remove writeclose for simpletags
    public static SimpleTag br() {
        return new SimpleTag("br");
    }

    public static SimpleTag col() {
        return new SimpleTag("col");
    }

    public static SimpleTag document() {
        return new SimpleTag("!DOCTYPE html");
    }

    public static SimpleTag hr() {
        return new SimpleTag("hr");
    }

    //TODO: fix text
    public static Text text(String text) {
        return new Text(text);
    }

    //TAGS
    public static Tag a() {
        return new Tag("a");
    }

    public static Tag abbr() {
        return new Tag("abbr");
    }

    public static Tag acronym() {
        return new Tag("acronym");
    }

    public static Tag address() {
        return new Tag("address");
    }

    public static Tag area() {
        return new Tag("area");
    }

    public static Tag b() {
        return new Tag("b");
    }

    public static Tag base() {
        return new Tag("base");
    }

    public static Tag bdo() {
        return new Tag("bdo");
    }

    public static Tag big() {
        return new Tag("big");
    }

    public static Tag blockquote() {
        return new Tag("blockquote");
    }

    public static Tag body() {
        return new Tag("body");
    }

    public static Tag button() {
        return new Tag("button");
    }

    public static Tag caption() {
        return new Tag("caption");
    }

    public static Tag cite() {
        return new Tag("cite");
    }

    public static Tag code() {
        return new Tag("code");
    }

    public static Tag colgroup() {
        return new Tag("colgroup");
    }

    public static Tag dd() {
        return new Tag("dd");
    }

    public static Tag del() {
        return new Tag("del");
    }

    public static Tag dfn() {
        return new Tag("dfn");
    }

    public static Tag dir() {
        return new Tag("dir");
    }

    public static Tag div() {
        return new Tag("div");
    }

    public static Tag dl() {
        return new Tag("dl");
    }

    public static Tag dt() {
        return new Tag("dt");
    }

    public static Tag em() {
        return new Tag("em");
    }

    public static Tag fieldset() {
        return new Tag("fieldset");
    }

    public static Tag form() {
        return new Tag("form");
    }

    public static Tag h1() {
        return new Tag("h1");
    }

    public static Tag h2() {
        return new Tag("h2");
    }

    public static Tag h3() {
        return new Tag("h3");
    }

    public static Tag h4() {
        return new Tag("h4");
    }

    public static Tag h5() {
        return new Tag("h5");
    }

    public static Tag h6() {
        return new Tag("h6");
    }

    public static Tag head() {
        return new Tag("head");
    }

    public static Tag html() {
        return new Tag("html");
    }

    public static Tag i() {
        return new Tag("i");
    }

    public static Tag iframe() {
        return new Tag("iframe");
    }

    public static Tag img() {
        return new Tag("img");
    }

    public static Tag input() {
        return new Tag("input");
    }

    public static Tag ins() {
        return new Tag("ins");
    }

    public static Tag kbd() {
        return new Tag("kbd");
    }

    public static Tag label() {
        return new Tag("label");
    }

    public static Tag li() {
        return new Tag("li");
    }

    public static Tag link() {
        return new Tag("link");
    }

    public static Tag map() {
        return new Tag("map");
    }

    public static Tag meta() {
        return new Tag("meta");
    }

    public static Tag noscript() {
        return new Tag("noscript");
    }

    public static Tag object() {
        return new Tag("object");
    }

    public static Tag ol() {
        return new Tag("ol");
    }

    public static Tag optgroup() {
        return new Tag("optgroup");
    }

    public static Tag option() {
        return new Tag("option");
    }

    public static Tag p() {
        return new Tag("p");
    }

    public static Tag param() {
        return new Tag("param");
    }

    public static Tag pre() {
        return new Tag("pre");
    }

    public static Tag q() {
        return new Tag("q");
    }

    public static Tag s() {
        return new Tag("s");
    }

    public static Tag samp() {
        return new Tag("samp");
    }

    public static Tag script() {
        return new Tag("script");
    }

    public static Tag select() {
        return new Tag("select");
    }

    public static Tag small() {
        return new Tag("small");
    }

    public static Tag span() {
        return new Tag("span");
    }

    public static Tag strike() {
        return new Tag("strike");
    }

    public static Tag strong() {
        return new Tag("strong");
    }

    public static Tag style() {
        return new Tag("style");
    }

    public static Tag sub() {
        return new Tag("sub");
    }

    public static Tag sup() {
        return new Tag("sup");
    }

    public static Tag table() {
        return new Tag("table");
    }

    public static Tag tbody() {
        return new Tag("tbody");
    }

    public static Tag td() {
        return new Tag("td");
    }

    public static Tag textarea() {
        return new Tag("textarea");
    }

    public static Tag tfoot() {
        return new Tag("tfoot");
    }

    public static Tag th() {
        return new Tag("th");
    }

    public static Tag thead() {
        return new Tag("thead");
    }

    public static Tag title() {
        return new Tag("title");
    }

    public static Tag tr() {
        return new Tag("tr");
    }

    public static Tag tt() {
        return new Tag("tt");
    }

    public static Tag u() {
        return new Tag("u");
    }

    public static Tag ul() {
        return new Tag("ul");
    }

    public static Tag var() {
        return new Tag("var");
    }


}
