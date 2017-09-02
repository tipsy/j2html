package j2html.utils;

@FunctionalInterface
public interface TextIndenter {
    String indent(int level, String text);
}
