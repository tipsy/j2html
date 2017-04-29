package j2html.tags;

import java.util.Arrays;
import java.util.List;

class TagCreatorCodeGenerator {

    public static void main(String[] args) {

        tags().forEach(tag -> {

            String a1 = "public static ContainerTag " + tag + "()";
            String a2 = "{ return new ContainerTag(\"" + tag + "\"); }";

            String b1 = "public static ContainerTag " + tag + "(String text)";
            String b2 = "{ return new ContainerTag(\"" + tag + "\").withText(text); }";

            String c1 = "public static ContainerTag " + tag + "(DomContent... dc)";
            String c2 = "{ return new ContainerTag(\"" + tag + "\").with(dc); }";

            System.out.println(String.format("%-60s%20s", a1, a2));
            System.out.println(String.format("%-60s%20s", b1, b2));
            System.out.println(String.format("%-60s%20s", c1, c2));
            System.out.println("");

        });

    }

    //  This is a method that contains all ContainerTags, there is nothing below it
    private static List<String> tags() {
        return Arrays.asList(
                "a",
                "abbr",
                "address",
                "article",
                "aside",
                "audio",
                "b",
                "bdi",
                "bdo",
                "blockquote",
                "body",
                "button",
                "canvas",
                "caption",
                "cite",
                "code",
                "colgroup",
                "datalist",
                "dd",
                "del",
                "details",
                "dfn",
                "dialog",
                "div",
                "dl",
                "dt",
                "em",
                "fieldset",
                "figcaption",
                "figure",
                "footer",
                "form",
                "h1",
                "h2",
                "h3",
                "h4",
                "h5",
                "h6",
                "head",
                "header",
                "html",
                "i",
                "iframe",
                "ins",
                "kbd",
                "label",
                "legend",
                "li",
                "main",
                "map",
                "mark",
                "menu",
                "menuitem",
                "meter",
                "nav",
                "noscript",
                "object",
                "ol",
                "optgroup",
                "option",
                "output",
                "p",
                "pre",
                "progress",
                "q",
                "rp",
                "rt",
                "ruby",
                "s",
                "samp",
                "script",
                "section",
                "select",
                "small",
                "span",
                "strong",
                "style",
                "sub",
                "summary",
                "sup",
                "table",
                "tbody",
                "td",
                "textarea",
                "tfoot",
                "th",
                "thead",
                "time",
                "title",
                "tr",
                "u",
                "ul",
                "var",
                "video"
        );
    }
}
