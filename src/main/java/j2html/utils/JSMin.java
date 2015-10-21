package j2html.utils;

import com.google.javascript.jscomp.*;
import com.google.javascript.jscomp.Compiler;

public class JSMin {

    public static String compressJs(String code, String sourcePath) {
        com.google.javascript.jscomp.Compiler compiler = new Compiler();
        CompilerOptions options = new CompilerOptions();
        CompilationLevel.SIMPLE_OPTIMIZATIONS.setOptionsForCompilationLevel(options);
        SourceFile extern = SourceFile.fromCode("externs.js", "");
        SourceFile input = SourceFile.fromCode(sourcePath, code);
        compiler.compile(extern, input, options);
        return compiler.toSource();
    }

}
