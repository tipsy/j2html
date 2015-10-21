package j2html.tags;

import j2html.utils.*;
import java.nio.file.*;
import static j2html.TagCreator.*;

public class InlineStaticResource {

    public enum TargetFormat { CSS_MIN, CSS, JS_MIN, JS }

    public static ContainerTag get(String path, TargetFormat format) {
        ContainerTag errorAlert = script().with(unsafeHtml("alert('Unable to read file. File: \"" + path + "\", Type: \"" + format + "\"')"));
        String fileString = getFileAsString(path);
        if(fileString != null) {
            switch(format) {
                case CSS_MIN : return style().with(unsafeHtml(compressCss(fileString)));
                case JS_MIN  : return script().with(unsafeHtml(compressJs(fileString, path)));
                case CSS     : return style().with(unsafeHtml(fileString));
                case JS      : return script().with(unsafeHtml(fileString));
                default      : return errorAlert;
            }
        }
        return errorAlert;
    }

    public static String getFileAsString(String path) {
        try {
            return new String(Files.readAllBytes(Paths.get(InlineStaticResource.class.getResource(path).toURI())), "UTF-8");
        } catch (Exception e) {
            return null;
        }
    }

    private static String compressCss(String code) {
        return CSSMin.compress(code);
    }

    private static String compressJs(String code, String debugPath) {
        return JSMin.compressJs(code, debugPath);
    }

}

