package j2html.tags;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DomContentJoiner {

    public static UnescapedText join(CharSequence delimiter, boolean fixPeriodAndCommaSpacing, Object... stringOrDomObjects) {
        String result = Stream.of(stringOrDomObjects).map(object -> {
            if (object instanceof String) {
                return ((String) object).trim();
            }
            if (object instanceof DomContent) {
                return ((DomContent) object).render().trim();
            }
            throw new RuntimeException("You can only join DomContent and String objects");
        }).collect(Collectors.joining(delimiter));
        if (fixPeriodAndCommaSpacing) {
            result = result.replaceAll("\\s\\.", ".");
            result = result.replaceAll("\\s\\,", ",");
        }
        return new UnescapedText(result.trim());
    }

}
