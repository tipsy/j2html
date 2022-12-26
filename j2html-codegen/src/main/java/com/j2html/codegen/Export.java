package com.j2html.codegen;

import com.j2html.codegen.model.AttrD;

import static com.j2html.codegen.generators.TagCreatorCodeGenerator.containerTags;
import static com.j2html.codegen.generators.TagCreatorCodeGenerator.emptyTags;
import static com.j2html.codegen.model.AttributesList.attributesDescriptive;
import static com.j2html.codegen.model.AttributesList.getCustomAttributesForHtmlTag;
import static java.lang.System.*;

public class Export {

    public static void main(String[] args){
        for (final String tag : emptyTags()) {
            out.print("EMPTY-ELEMENT[");
            out.print(tag);
            out.print("]");
            out.println();
        }
        for (final String tag : containerTags()) {
            out.print("ELEMENT[");
            out.print(tag);
            out.print("]");
            out.println();
        }

        out.println();

        for(AttrD attr : attributesDescriptive()){
            if(attr.hasArgument){
                out.print("STRING");
            }else{
                out.print("BOOLEAN");
            }
            out.print("[");
            out.print(attr.attr);
            out.print("]");
            out.println();
            for(String tag : attr.tags){
                out.print("ATTRIBUTE[");
                out.print(tag);
                out.print(":");
                out.print(attr.attr);
                out.print("]");
                out.println();
            }
            out.println();
        }
    }
}
