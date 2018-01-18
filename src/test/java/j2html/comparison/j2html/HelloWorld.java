package j2html.comparison.j2html;

import static j2html.TagCreator.attrs;
import static j2html.TagCreator.body;
import static j2html.TagCreator.h1;
import static j2html.TagCreator.head;
import static j2html.TagCreator.html;
import static j2html.TagCreator.link;
import static j2html.TagCreator.main;
import static j2html.TagCreator.title;

import j2html.tags.ContainerTag;

public class HelloWorld {

  public static ContainerTag tag =
      html(
          head(title("Title"), link().withRel("stylesheet").withHref("/css/main.css")),
          body(main(attrs("#main.content"), h1("Heading!"))));
}
