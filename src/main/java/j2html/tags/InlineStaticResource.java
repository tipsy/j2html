package j2html.tags;

import java.nio.file.Files;
import java.nio.file.Paths;

import j2html.utils.CSSMin;
import j2html.utils.JSMin;

import static j2html.TagCreator.script;
import static j2html.TagCreator.style;
import static j2html.TagCreator.unsafeHtml;

public class InlineStaticResource {

    public enum TargetFormat {CSS_MIN, CSS, JS_MIN, JS}

    public static ContainerTag get(String path, TargetFormat format) {
        ContainerTag errorAlert = script().with(unsafeHtml("alert('Unable to read file. File: \"" + path + "\", Type: \"" + format + "\"')"));
        String fileString = getFileAsString(path);
        if(fileString != null) {
            switch(format) {
                case CSS_MIN : return style().with(unsafeHtml(compressCss(fileString)));
                case JS_MIN  : return script().with(unsafeHtml(compressJs(fileString)));
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

    private static String compressJs(String code) {
        return JSMin.compressJs(code);
    }

}

