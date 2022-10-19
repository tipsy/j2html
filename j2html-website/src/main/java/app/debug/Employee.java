package app.debug;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Employee {
    public final int id;
    public final String name;
    public final String title;

    Employee(int id, String name, String title) {
        this.id = id;
        this.name = name;
        this.title = title;
    }

    public static List<Employee> employees = new ArrayList<Employee>(Arrays.asList(
        new Employee(1, "David", "Creator of Bad Libraries"),
        new Employee(2, "Christian", "Fanboi of Jenkins"),
        new Employee(3, "Paul", "Hater of Lambda Expressions")
    ));

}
