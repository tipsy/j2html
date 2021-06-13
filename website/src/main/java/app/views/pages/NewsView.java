package app.views.pages;

import app.views.MainView;
import j2html.TagCreator;
import j2html.tags.ContainerTag;
import j2html.tags.DomContent;
import java.util.Arrays;
import java.util.List;
import static app.views.Partials.mavenLink;
import static j2html.TagCreator.a;
import static j2html.TagCreator.article;
import static j2html.TagCreator.attrs;
import static j2html.TagCreator.br;
import static j2html.TagCreator.code;
import static j2html.TagCreator.dd;
import static j2html.TagCreator.dl;
import static j2html.TagCreator.dt;
import static j2html.TagCreator.each;
import static j2html.TagCreator.em;
import static j2html.TagCreator.h2;
import static j2html.TagCreator.join;
import static j2html.TagCreator.li;
import static j2html.TagCreator.p;
import static j2html.TagCreator.section;
import static j2html.TagCreator.text;
import static j2html.TagCreator.ul;

public class NewsView {

    private static ContainerTag newsPost(String title, String version, DomContent... listItems) {
        List<DomContent> list = Arrays.asList(listItems);
        return article(
            h2(title).withId("j2html-" + version + "-released"),
            mavenLink(version),
            p("Changes:"),
            ul(
                each(list, el -> TagCreator.li(el))
            ));
    }

    public static String render() {
        return MainView.render(
            "News about j2html",
            "News and releases",
            section(attrs("#news"),

                newsPost(
                    "j2html 1.4.0 adds a couple of convenient methods (Jan 2019)", "1.4.0",
                    join("Added", code("TagCreator::each(Map, BiFunction)")),
                    join("Added", code("Stream<DomContent>"), "variants of", code("each"), "and", code("with"))
                ),

                newsPost(
                    "j2html 1.3.0 has a couple of features and fixes (May 2018)", "1.3.0",
                    join("Added support for", code("Map"), "in", code("each()")),
                    join("Added osgi metadata"),
                    join("Added support for", code("Optional"), "in", code("iff()")),
                    join("Fixed bugs in ", code("renderFormatted()"))
                ),

                newsPost(
                    "j2html 1.2.2 has performance improvements (Dec 2017)", "1.2.2",
                    join("There have been some massive performance improvements. Big thanks to", a("kicktipp").withHref("https://github.com/kicktipp"), ".")
                ),

                newsPost(
                    "j2html 1.2.1 fixes some bugs (Nov 2017)", "1.2.1",
                    join("Fix", em("\"bad closing tag (<!DOCTYPE html/>) when closeEmptyTags is true\""), "bug"),
                    join("Fix", em("\"can't load static resources from jar\""), "bug"),
                    join("Fix", em("\"CSSMin stripping last character of CSS rule if rule doesn't end in semi-colon\""), "bug")
                ),

                newsPost(
                    "j2html 1.2.0 already? (Sep 2017)", "1.2.0",
                    join("Added option to render formatted HTML, ex", code("body(...).renderFormatted()")),
                    join("Added option to configure HTML-formatting-indent via", code("Config.indenter = (int, string) -> {...}")),
                    join("Added option to configure CSS-minification via", code("Config.cssMinifier = string -> {...}")),
                    join("Added option to configure JS-minification via", code("Config.jsMinifier = string -> {...}")),
                    join("Added option to close empty tags via", code("Config.closeEmptyTags = true")),
                    join(code("attr()"), "can now take take any object, not just strings")
                ),

                newsPost(
                    "j2html 1.1.0 is out! (Aug 2017)", "1.1.0",
                    join("Added a option to customize TextEscaper via", code("Config.textEscaper = text -> {}"))
                ),

                newsPost(
                    "j2html 1.0.0 is here! (May 2017)", "1.0.0",
                    join("v1 is officially done. We will be doing", a("semantic versioning").withHref("http://semver.org/").withTarget("_blank"), "from now on."),
                    join("All tag methods (", code("div()"), ",", code("p()"), "etc ) can now accept an arbitrary number of", code("DomContent"), "as arguments, eliminating the need for", code("with()"), "in most cases."),
                    join("Added a shorthand-attribute overloads to all TagCreator methods:", br(), code("div(attrs(\"#id.class\")"), "becomes", code("<div id=\"id\" class=\"class\"></div>")),
                    join("Added a", code("join()"), "method to more easily join sentences with inline HTML, like:", br(), code("join(\"Added a\", code(\"join()\"), \"method to more ea...")),
                    join("Added a", code("document()"), "method that takes a", code("html()"), " tag and renders a HTML declaration followed by the html content"),
                    join("Added support for Java 7 and Java 6. Some functionality (each/filter) will not work on these versions, but everything else should.")
                ),

                newsPost(
                    "j2html 0.99 released! (Apr 2017)", "0.99",
                    join("Added generic", code("iff()"), "/", code("iffElse()"), "methods for performing if's in method calls."),
                    join("Added", code("withClasses()"), "to add multiple classes to element. Works well with", code("iff()"), "."),
                    join("HTML-escaping is now a lot faster (and a lot faster than StringUtils)"),
                    join("Static files can now be fetched from anywhere, not just classpath")
                ),

                newsPost(
                    "j2html 0.88 released! (Jan 2017)", "0.88",
                    text("Closure and StringUtils dependencies removed in favor of custom implementations. Most users seem interested in a very lightweight library."),
                    code("unsafeHtml()"), text(" is now "), code("rawHtml()"),
                    text("Added "), code(".equals()"), text(" to Tag-class. Two Tags are equal if they render the same HTML")
                ),

                // ---------------------------------------------------------------------------------------------
                h2("j2html 0.7 released!").withId("j2html-0.7-released"),
                mavenLink("0.7"),
                p("Changes:"),
                ul(
                    li("New file-api (avilable from TagCreator class):").with(
                        dl(
                            dt("fileAsString(String path)"),
                            dd("returns a file's content as a String (useful for including static HTML such as Google Analytics code)"),

                            dt("fileAsEscapedString(String path)"),
                            dd("returns fileAsString(path) escaped (useful for including the code-examples on this website)"),

                            dt("styleWithInlineFile(String path)"),
                            dd("returns <style>fileAsString(path)</style>"),

                            dt("styleWithInlineFile_min(String path)"),
                            dd("returns <style>fileAsString(path)</style>, minimized using CSSMin"),

                            dt("scriptWithInlineFile(String path)"),
                            dd("returns <script>fileAsString(path)</script>"),

                            dt("scriptWithInlineFile_min(String path)"),
                            dd("returns <script>fileAsString(path)</script>, minimized using Google Closure")
                        )
                    ),
                    li("Java 7 support (j2html previously only worked with Java 8)"),
                    li("Use apache stringutils for escaping")
                ),
                // ---------------------------------------------------------------------------------------------
                h2("j2html 0.5.0 is on Maven! (Late May 2015)").withId("j2html-on-maven"),
                mavenLink("0.5.0"),
                p("We've finished the herculean task of releasing on Maven Central.")
            )
        );
    }
}
