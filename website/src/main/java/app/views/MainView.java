package app.views;

import j2html.tags.DomContent;

import static j2html.TagCreator.*;

public class MainView {

    public static String render(String title, String heading, DomContent... tags) {
        return document(
            html(
                head(
                    meta().withCharset("UTF-8"),
                    meta().withName("viewport").withContent("width=device-width, initial-scale=1"),
                    meta().withName("description").withContent("j2html - Fast and fluent Java HTML builder. Build type-safe HTML 5 with Java 8 expression!"),
                    title(title + " - Java HTML builder"),
                    link().withRel("icon").withHref("/img/favicon.svg"),
                    link().withRel("stylesheet").withHref("https://fonts.googleapis.com/css2?family=Lato&family=Patua+One&display=swap"),
                    styleWithInlineFile_min("/public/css/prism.css"),
                    styleWithInlineFile_min("/public/css/main.css")
                ),
                body(
                    fileAsString("/html/googleTagManager.html"),
                    fileAsString("/html/githubBanner.html"),
                    header(attrs(".top-header"),
                           nav(attrs(".width-limit"),
                               a().withId("logo").withHref("/").with(
                                   span(img().withSrc("/img/logo.svg").withAlt("j2html logo"))
                               ),
                               ul(
                                   li(a("Home").withHref("/")),
                                   li(a("Download").withHref("/download.html")),
                                   li(a("Examples").withHref("/examples.html")),
                                   li(a("News").withHref("/news.html"))
                               )
                           )

                    ),
                    header(attrs(".banner"),
                           h1(attrs(".width-limit"), heading)
                    ),
                    main(attrs(".width-limit"),
                         tags //content from other template
                    ),
                    div(attrs("#javalin-suggestion"), join(
                        span("✖").withClass("close"),
                        "Want a simple and modern web framework?",
                        br(),
                        "Try our other project:",
                        a("https://javalin.io").withHref("https://javalin.io?from=j2html")
                    )),
                    footer(join(
                        "This page was created using",
                        a("j2html").withHref("https://github.com/tipsy/j2html").withTarget("_blank"),
                        "and",
                        a("Javalin").withHref("https://javalin.io/").withTarget("_blank"),
                        ". Webpage source on",
                        a("Github").withHref("https://github.com/j2html/j2html-webpage").withTarget("_blank"),
                        ".",
                        br(),
                        p(attrs(".lols"), "A static page generator or a template engine would be better suited than a HTML builder for creating this page, but we had to do it.")
                    )),
                    scriptWithInlineFile("/public/js/prism.js"),
                    scriptWithInlineFile_min("/public/js/codeCompare.js"),
                    scriptWithInlineFile_min("/public/js/javalinSuggestion.js")
                )
            )
        );
    }

}

