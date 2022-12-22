package com.j2html.codegen.wattsi;

public interface AttributeDefinition {

    String name();

    boolean appliesTo(ElementDefinition element);

    boolean isObsolete();
}
