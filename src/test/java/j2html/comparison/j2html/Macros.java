package j2html.comparison.j2html;

import j2html.tags.ContainerTag;
import j2html.tags.DomContent;
import static j2html.TagCreator.attrs;
import static j2html.TagCreator.body;
import static j2html.TagCreator.div;
import static j2html.TagCreator.h1;
import static j2html.TagCreator.head;
import static j2html.TagCreator.html;
import static j2html.TagCreator.link;
import static j2html.TagCreator.main;
import static j2html.TagCreator.title;

public class Macros {

    public static ContainerTag tag = mainLayout(div(h1("Example content"), someMacro(1), someMacro(2), someMacro(3)));

    private static ContainerTag mainLayout(DomContent content) {
        return html(head(title("Title"), link().withRel("stylesheet").withHref("/css/main.css")), body(main(attrs("#main.content"), content)));
    }

    private static ContainerTag someMacro(int i) {
        return div("Macro call " + i);
    }

}
