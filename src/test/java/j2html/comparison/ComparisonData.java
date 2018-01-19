package j2html.comparison;

import j2html.comparison.model.Employee;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ComparisonData {

    public static List<Employee> fiveHundredEmployees() {
        return IntStream.range(0, 500).mapToObj(i -> new Employee(i, "Some name", "Some title")).collect(Collectors.toList());
    }

    public static List<Integer> tableNumbers = IntStream.range(1, 51).boxed().collect(Collectors.toList());

}
