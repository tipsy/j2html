package j2html.comparison.j2html;

import j2html.comparison.ComparisonData;
import j2html.tags.ContainerTag;
import static j2html.TagCreator.each;
import static j2html.TagCreator.li;
import static j2html.TagCreator.ul;
import static org.apache.commons.lang3.StringUtils.join;

public class FiveHundredEmployees {

    public static ContainerTag tag = ul(
        each(ComparisonData.fiveHundredEmployees(), employee ->
            li(join(employee.getId(), employee.getName(), employee.getTitle()))
        )
    );

}
