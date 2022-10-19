package j2html.comparison.j2html;

import j2html.comparison.ComparisonData;
import j2html.tags.specialized.UlTag;

import static j2html.TagCreator.each;
import static j2html.TagCreator.li;
import static j2html.TagCreator.ul;
import static java.lang.String.join;
import static java.lang.String.valueOf;

public class FiveHundredEmployees {

    public static UlTag tag = ul(
        each(ComparisonData.fiveHundredEmployees(), employee ->
            li(join(" ", valueOf(employee.getId()), employee.getName(), employee.getTitle()))
        )
    );

}
