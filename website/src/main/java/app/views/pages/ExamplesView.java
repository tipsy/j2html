package app.views.pages;

import app.views.MainView;
import java.util.Arrays;
import java.util.List;
import static app.views.Partials.codeSnippet;
import static app.views.Partials.javaComparison;
import static j2html.TagCreator.a;
import static j2html.TagCreator.attrs;
import static j2html.TagCreator.each;
import static j2html.TagCreator.em;
import static j2html.TagCreator.fileAsEscapedString;
import static j2html.TagCreator.h2;
import static j2html.TagCreator.p;
import static j2html.TagCreator.section;
import static j2html.TagCreator.table;
import static j2html.TagCreator.tbody;
import static j2html.TagCreator.td;
import static j2html.TagCreator.text;
import static j2html.TagCreator.tr;

public class ExamplesView {
    private static List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 9, 10);

    public static String render() {
        return MainView.render(
            "Examples of how to use j2html",
            "Reclaim control of your HTML",
            section(attrs("#examples"),
                h2(attrs("#basic-example"), "Basic example"),
                p("Creating a basic HTML structure in j2html is pretty similar to plain HTML. This Java code:"),
                javaComparison("basic"),
                p("Becomes this HTML:"),
                codeSnippet("markup", fileAsEscapedString("/codeExamples/basic.html")),
                p(
                    text(
                        "It's literally impossible to forget to close a div, mistype an attribute name, or forget an attribute quote! " +
                            "Remember to include the Java wrapping code though, j2html is not a template language, all files are .java. " +
                            "To see how the wrapping code could look, check out the "
                    ),
                    a("getting started example").withHref("/"),
                    text(".")
                ),

                h2(attrs("#core-concepts"), "Core concepts"),
                codeSnippet("java", fileAsEscapedString("/codeExamples/coreConcepts.java")),

                h2(attrs("#loops"), "Loops, each() and filter()"),
                p("Using Java 8's lambda syntax, you can write loops (via streams) inside your HTML-builder:"),
                javaComparison("forLoopLambda"),

                p("j2html also offers a custom each method, which is slightly more powerful:"),
                javaComparison("each"),

                p("If you need to filter your collection, j2html has a built in filter function too:"),
                javaComparison("filter"),

                p(
                    "Since this is pure Java, all the Employee methods (getName, getImgPath, getTitle) are available to you, " +
                        "and you get autocomplete suggestions and compile time errors."
                ),
                p("Given three random employees, all the above approaches would give the same HTML:"),
                codeSnippet("markup", fileAsEscapedString("/codeExamples/forLoop.html")),

                h2(attrs("#2d-table-example"), "Two dimensional table example"),
                javaComparison("2dTable"),
                p("The code above is generating this table:"),
                table(attrs("#table-example"),
                    tbody(
                        each(numbers, i -> tr(
                            each(numbers, j -> td(
                                String.valueOf(i * j)
                            ))
                        ))
                    )
                ),

                h2(attrs("#partials"), "Partials"),
                p("You can create partials for elements you use a lot:"),
                javaComparison("partial"),
                p("The equivalent HTML would be:"),
                codeSnippet("markup", fileAsEscapedString("/codeExamples/partial.html")),
                p("You can then use these partials, for example in a registration form:"),
                javaComparison("view"),
                p("Pretty clean, right? The rendered HTML is more verbose:"),
                codeSnippet("markup", fileAsEscapedString("/codeExamples/view.html")),
                p(
                    text("Imagine if you wanted labels in addition. The Java snippet would look almost identical: You could create a partial called"),
                    em(" passwordAndLabel() "),
                    text(
                        "and nothing but the method name would change. The resulting HTML however, would be twice or thrice as big, " +
                            "depending on whether or not you wrapped the input and label in another tag."
                    )
                ),

                h2(attrs("#dynamic-views"), "Dynamic views"),
                p(
                    "Once you've set up partials, you can call them from wherever, which greatly reduces potential errors. " +
                        "Let's say we want to include the form from the partials-example in our webpage. " +
                        "We want a header above and a footer below. A lot of templating languages make you do this: "
                ),
                codeSnippet("java", fileAsEscapedString("/codeExamples/otherTemplates.vm")),
                p(
                    "This is a pain to work with. You have no idea what the header and footer expects, and you have no way to affect how they treat your content. " +
                        "You can easily break the site by forgetting to close divs, or by forgetting to include either the header or the footer in one of your views. " +
                        "In j2html you can specify the context in which a view is rendered, and supply the rendering method with type safe parameters! " +
                        "If we want to insert our form in a header/footer frame, we simply create a MainView and make it take our view as an argument:"
                ),

                javaComparison("main"),
                p("Which will result in the rendered HTML:"),
                codeSnippet("markup", fileAsEscapedString("/codeExamples/main.html")),
                p(
                    "We would now get a compilation error if we forgot to include a title, and there is 0 chance of forgetting either header or footer, mistyping paths" +
                        ", forgetting to close divs, or anything else."
                )

            )
        );
    }
}
