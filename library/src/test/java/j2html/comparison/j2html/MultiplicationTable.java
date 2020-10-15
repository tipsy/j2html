package j2html.comparison.j2html;

import j2html.comparison.ComparisonData;
import j2html.tags.ContainerTag;
import static j2html.TagCreator.each;
import static j2html.TagCreator.table;
import static j2html.TagCreator.tbody;
import static j2html.TagCreator.td;
import static j2html.TagCreator.tr;

public class MultiplicationTable {

    public static ContainerTag tag = table(
        tbody(
            each(ComparisonData.tableNumbers, i -> tr(
                each(ComparisonData.tableNumbers, j -> td(
                    String.valueOf(i * j)
                ))
            ))
        )
    );

}
