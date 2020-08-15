package j2html.comparison.model;

public class Employee {
    int id;
    String name;
    String title;

    public Employee(int id, String name, String title) {
        this.id = id;
        this.name = name;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }
}
