package j2html.utils;

import com.google.javascript.jscomp.CompilationLevel;
import com.google.javascript.jscomp.SourceFile;
import com.google.javascript.jscomp.CommandLineRunner;
import com.google.javascript.jscomp.CompilerOptions;
import com.google.javascript.jscomp.WarningLevel;
import com.google.javascript.jscomp.Compiler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class can be used to compress JS code using Google Closure Compiler.
 * To learn more about GCC, please visit https://github.com/google/closure-compiler.
 */
public class JSMinGCC {
    private CompilationLevel compilationLevel = CompilationLevel.SIMPLE_OPTIMIZATIONS;
    private List<SourceFile> externs = new ArrayList<>();
    private static final String INPUT_NAME = "input.js";

    public JSMinGCC() {
    }

    public JSMinGCC(CompilationLevel compilationLevel, List<SourceFile> externs) {
        this.compilationLevel = compilationLevel;
        this.externs = externs;
    }

    /**
     * Method can be used to change compilation level.
     * Compilation level identifies witch algorithm will be used to compress js code.
     *
     * @param compilationLevelToSet compilation level to set
     */
    public void setCompilationLevel(CompilationLevel compilationLevelToSet) {
        compilationLevel = compilationLevelToSet;
    }

    /**
     * Method can be used to add external js files (externs).
     * Externs are declarations that tell Closure Compiler the names of symbols
     * that should not be renamed during advanced compilation.
     *
     * @param externsToSet externs to set
     */
    public void setExterns(List<SourceFile> externsToSet) {
        externs = externsToSet;
    }

    /**
     * Method can be used to get default browser environment variables.
     * It is recommended to use browser externs when Advanced compilation level is set
     */
    public static List<SourceFile> getBrowserExterns() {
        List<SourceFile> result = new ArrayList<>();
        try {
            result.addAll(CommandLineRunner.getBuiltinExterns(CompilerOptions.Environment.BROWSER));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Method compresses js string using Google Closure Compiler with given settings.
     *
     * @param code the js-code you want to compress
     * @return the compressed code
     */
    public String compressJS(String code) {
        com.google.javascript.jscomp.Compiler compiler = new Compiler();
        CompilerOptions options = new CompilerOptions();
        options.setLanguageOut(CompilerOptions.LanguageMode.STABLE_OUT);
        WarningLevel.QUIET.setOptionsForWarningLevel(options);
        compilationLevel.setOptionsForCompilationLevel(options);
        List<SourceFile> inputs = Collections.singletonList(SourceFile.fromCode(INPUT_NAME, code));
        compiler.compile(externs, inputs, options);
        return compiler.toSource();
    }
}
