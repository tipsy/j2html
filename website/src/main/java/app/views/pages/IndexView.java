package app.views.pages;

import app.views.MainView;

import static app.views.Partials.*;
import static j2html.TagCreator.*;

public class IndexView {
    public static String render() {
        return MainView.render(
            "Fast and fluent Java HTML5 builder",
            "Fast and fluent Java HTML5 builder",
            section(attrs("#index"),
                    h2("Getting started with j2html"),
                    p("Import TagCreator to get started. j2html's syntax is fluent and closely matched to HTML:"),
                    codeSnippet("java", fileAsEscapedString("/codeExamples/minimal.java")),
                    p("The Java code above becomes the HTML below:"),
                    codeSnippet("markup", fileAsEscapedString("/codeExamples/minimal.html")),
                    p(
                        text("Check out some more "),
                        a("examples").withHref("/examples.html"),
                        text(".")
                    ),

                    h2("Intended use"),
                    h3("Consider using j2html if:"),
                    ul(
                        li("You love type safety. You love catching errors at compile time instead of waiting for some poor user to notice that something is wrong"),
                        li("You like to dynamically reuse your view code"),
                        li(
                            text("You think template engines are too slow. This index page was rendered 100 000 times in less than a second on an i5-4670. That's about a thousand times faster than Apache '"),
                            a("Velocity").withHref("http://velocity.apache.org/").withTarget("_blank"),
                            text("' (hah!)")
                        )
                    ),

                    h3("Be careful about using j2html if:"),
                    ul(
                        li("You don't know Java and HTML well"),
                        li("You're creating a classic \"website\" that has a lot of static HTML (if it's all generated then it's fine)"),
                        li("Your application has a lot of text and you don't use language files / a database (string concatenation would get very annoying)"),
                        li("You use a CSS framework which relies on a lot of copy pasting HTML from docs. You'll have to translate these snippets to j2html")
                    ),

                    h2("Why did you make this library?"),
                    p(
                        "First: j2html is a Java HTML builder. It's not a template engine, and it doesn't want to compete with template engines. "
                            + "We were looking for a good way to create HTML for a complex login solution which had many different forms "
                            + "(with different configurations, depending on user state and user actions, etc.), but very little actual HTML per page. "
                            + "The result was j2html. We decided to release the Java HTML builder we made, since it seems better "
                            + "than all the other Java HTML builders we found while researching the subject. Hopefully someone will find it useful!"
                    )
            )
        );
    }
}
