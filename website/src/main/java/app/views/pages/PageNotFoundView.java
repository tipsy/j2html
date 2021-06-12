package app.views.pages;

import app.views.MainView;
import static j2html.TagCreator.attrs;
import static j2html.TagCreator.h2;
import static j2html.TagCreator.p;
import static j2html.TagCreator.section;

public class PageNotFoundView {
    public static String render() {
        return MainView.render(
            "Page not found",
            "Page not found",
            section(attrs("#not-found"),
                h2("Use the menu to get back on track!"),
                p(attrs(".error-code"), "Error code 404")
            )
        );
    }
}
