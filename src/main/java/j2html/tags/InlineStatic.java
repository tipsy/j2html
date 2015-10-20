package j2html.tags;

import com.google.javascript.jscomp.*;
import com.google.javascript.jscomp.Compiler;
import j2html.utils.*;
import java.nio.file.*;
import static j2html.TagCreator.*;

public class InlineStatic {

    public static ContainerTag getInlineResource(String path, String type) {
        ContainerTag errorTag = script().with(unsafeHtml("alert('Unable to read file. File: \"" + path + "\", Type: \"" + type + "\"')"));
        try {
            String fileString = new String(Files.readAllBytes(Paths.get(InlineStatic.class.getResource(path).toURI())), "UTF-8");
            switch(type) {
                case "css.min" : return style().with(unsafeHtml(compressCss(fileString)));
                case "js.min"  : return script().with(unsafeHtml(compressJs(fileString, path)));
                case "css"     : return style().with(unsafeHtml(fileString));
                case "js"      : return script().with(unsafeHtml(fileString));
                default        : return errorTag;
            }
        } catch (Exception e) {
            return errorTag;
        }
    }

    private static String compressCss(String code) {
        return CSSMin.compress(code);
    }

    private static String compressJs(String code, String sourcePath) {
        Compiler compiler = new Compiler();
        CompilerOptions options = new CompilerOptions();
        CompilationLevel.SIMPLE_OPTIMIZATIONS.setOptionsForCompilationLevel(options);
        SourceFile extern = SourceFile.fromCode("externs.js", "");
        SourceFile input = SourceFile.fromCode(sourcePath, code);
        compiler.compile(extern, input, options);
        return compiler.toSource();
    }

}
