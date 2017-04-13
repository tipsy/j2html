package j2html.tags;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import j2html.utils.CSSMin;
import j2html.utils.JSMin;

import static j2html.TagCreator.rawHtml;
import static j2html.TagCreator.script;
import static j2html.TagCreator.style;

public class InlineStaticResource {

    public enum TargetFormat {CSS_MIN, CSS, JS_MIN, JS}

    public static ContainerTag get(String path, TargetFormat format) {
        String fileString = getFileAsString(path);
        switch (format) {
            case CSS_MIN : return style().with(rawHtml(CSSMin.compress(fileString)));
            case JS_MIN  : return script().with(rawHtml(JSMin.compressJs(fileString)));
            case CSS     : return style().with(rawHtml(fileString));
            case JS      : return script().with(rawHtml(fileString));
            default      : throw new RuntimeException("Invalid target format");
        }
    }

    public static String getFileAsString(String path) {
        try {
            return readFileAsString(Paths.get(InlineStaticResource.class.getResource(path).toURI()));
        } catch (Exception e1) {
            try {
                return readFileAsString(Paths.get(path));
            } catch (Exception e2) {
                throw new RuntimeException("Couldn't find file with path='" + path + "'");
            }
        }
    }

    private static String readFileAsString(Path path) throws IOException {
        return new String(Files.readAllBytes(path), "UTF-8");
    }

}

