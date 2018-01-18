package j2html.comparison.j2html;

import static j2html.TagCreator.*;

import j2html.comparison.ComparisonData;
import j2html.tags.ContainerTag;

public class MultiplicationTable {

  public static ContainerTag tag =
      table(
          tbody(
              each(
                  ComparisonData.tableNumbers,
                  i -> tr(each(ComparisonData.tableNumbers, j -> td(String.valueOf(i * j)))))));
}
