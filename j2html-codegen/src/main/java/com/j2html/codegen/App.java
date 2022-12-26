package com.j2html.codegen;

import com.j2html.codegen.generators.SpecializedTagClassCodeGenerator;
import com.j2html.codegen.generators.AttributeInterfaceCodeGenerator;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class App
{
    public static void main( String[] args )
    {
        final Path relPath = Paths.get("../library/src/main/java/j2html/");
        final Path absPath = relPath.toAbsolutePath();

        System.out.println("writing in "+absPath);

        //decide if the files should be
        //deleted or generated
        final boolean delete = false;

        try {
            AttributeInterfaceCodeGenerator.generate(absPath, delete);
            SpecializedTagClassCodeGenerator.generate(absPath, delete);
            //TagCreatorCodeGenerator.print();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //don't forget to auto-reformat the generated code.
    }
}
