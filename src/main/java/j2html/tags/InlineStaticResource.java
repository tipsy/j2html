package j2html.tags;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import j2html.utils.CSSMin;
import j2html.utils.JSMin;

import static j2html.TagCreator.*;

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
            return readFileAsString(InlineStaticResource.class.getResource(path).getPath());
        } catch (Exception e1) {
            try {
                return readFileAsString(path);
            } catch (Exception e2) {
                throw new RuntimeException("Couldn't find file with path='" + path + "'");
            }
        }
    }

    /**
     * @author kjheimark <3
     */
    private static String readFileAsString(String path) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        StringBuilder sb = new StringBuilder();
        int c;
        while ((c = bufferedReader.read()) >= 0 && c >= 0) {
            sb.append((char) c);
        }
        return sb.toString();
    }

}

