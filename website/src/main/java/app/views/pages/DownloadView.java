package app.views.pages;

import app.views.MainView;
import static app.views.Partials.codeSnippet;
import static j2html.TagCreator.*;

public class DownloadView {
    public static String render() {
        return MainView.render(
            "Download j2html",
            "Maven and GitHub",
            section(attrs("#download"),
                h2("Maven dependency"),
                p("To experience the joy of generating HTML with a Java HTML builder, add the j2html dependency to your POM:"),
                codeSnippet("markup", fileAsEscapedString("/codeExamples/mavenDep.xml")),
                a().withId("upgrade"),
                h2("Steps for upgrading"),
                p("From 1.4.0 to 1.5.0"),
                ul(
                    li(
                        join("Change return types from", code("Tag"), ",", code("ContainerTag"), "or", code("EmptyTag"), "to the specific tag being returned.")
                    ),
                    li(
                        join("Change missing method calls on tags, such as", code("withRole(\"value\")"), "to", code(".attr(\"role\", \"value\")"), ".")
                    ),
                    li(
                        join("Method parameters of", code("Tag"), ",", code("ContainerTag"), "or", code("EmptyTag"), "should have a wildcard (&lt;?&gt;) added, or be changed to a specific tag.")
                    ),
                    li(
                        join("Replace ambiguous method references like ", code("each(list, TagCreator::li)"), " with lambdas such as", code("each(list, str -> li(str))"), ".")
                    )
                ),
                h2("Clone the repo on GitHub"),
                p(join(
                    "Please clone and/or fork the repo on",
                    a("GitHub").withHref("https://github.com/tipsy/j2html").withTarget("_blank"),
                    ", make changes, and create pull requests! We will go through pull requests every sunday, so don't be shy."
                ))

            )
        );
    }
}
