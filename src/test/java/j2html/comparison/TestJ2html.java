package j2html.comparison;

import j2html.comparison.j2html.FiveHundredEmployees;
import j2html.comparison.j2html.HelloWorld;
import j2html.comparison.j2html.Macros;
import j2html.comparison.j2html.MultiplicationTable;

public class TestJ2html {

  public static String helloWorld() {
    return HelloWorld.tag.render();
  }

  public static String fiveHundredEmployees() {
    return FiveHundredEmployees.tag.render();
  }

  public static String macros() {
    return Macros.tag.render();
  }

  public static String multiplicationTable() {
    return MultiplicationTable.tag.render();
  }

  public static void main(String[] args) {
    System.out.println(MultiplicationTable.tag.renderFormatted());
  }
}
