package app.views;

import j2html.tags.ContainerTag;
import j2html.tags.Text;
import static j2html.TagCreator.a;
import static j2html.TagCreator.attrs;
import static j2html.TagCreator.code;
import static j2html.TagCreator.div;
import static j2html.TagCreator.fileAsEscapedString;
import static j2html.TagCreator.join;
import static j2html.TagCreator.li;
import static j2html.TagCreator.p;
import static j2html.TagCreator.pre;
import static j2html.TagCreator.ul;

public class Partials {

    public static ContainerTag codeSnippet(String language, Text snippet) {
        return pre(
            code().withClass("language-" + language).with(
                snippet
            )
        );
    }

    public static ContainerTag javaComparison(String filename) {
        return div().withClasses("code-compare", "nowith").with(
            ul(
                li("version 1.0.0 +").withClass("nowith-switch active"),
                li("earlier versions").withClass("with-switch")
            ),
            pre(attrs(".nowith-pre"),
                code(attrs(".language-java"),
                    fileAsEscapedString("/codeExamples/" + filename + "_new.java")
                )
            ),
            pre(attrs(".with-pre"),
                code(attrs(".language-java"),
                    fileAsEscapedString("/codeExamples/" + filename + ".java")
                )
            )
        );
    }

    public static ContainerTag mavenLink(String version) {
        return p(join(
            "j2html", version, "is available for download on",
            a("Maven Central").withHref("http://search.maven.org/#artifactdetails%7Ccom.j2html%7Cj2html%7C" + version + "%7Cjar").withTarget("_blank"),
            "."
        ));
    }
}
