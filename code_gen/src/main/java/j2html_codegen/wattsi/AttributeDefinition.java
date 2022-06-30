package j2html_codegen.wattsi;

public interface AttributeDefinition {

    String name();

    boolean appliesTo(ElementDefinition element);

    boolean isObsolete();
}
