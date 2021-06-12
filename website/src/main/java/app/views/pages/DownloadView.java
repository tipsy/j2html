package app.views.pages;

import app.views.MainView;
import static app.views.Partials.codeSnippet;
import static j2html.TagCreator.a;
import static j2html.TagCreator.attrs;
import static j2html.TagCreator.fileAsEscapedString;
import static j2html.TagCreator.h2;
import static j2html.TagCreator.join;
import static j2html.TagCreator.p;
import static j2html.TagCreator.section;

public class DownloadView {
    public static String render() {
        return MainView.render(
            "Download j2html",
            "Maven and GitHub",
            section(attrs("#download"),
                h2("Maven dependency"),
                p("To experience the joy of generating HTML with a Java HTML builder, add the j2html dependency to your POM:"),
                codeSnippet("markup", fileAsEscapedString("/codeExamples/mavenDep.xml")),
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
