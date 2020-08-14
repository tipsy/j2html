package j2html.tags;

import java.util.Arrays;
import java.util.List;

class TagCreatorCodeGenerator {

    public static void main(String[] args) {
        System.out.println("// EmptyTags, generated in " + TagCreatorCodeGenerator.class);
        for (String tag : emptyTags()) {
            String emptyA1 = "public static EmptyTag " + tag + "()";
            String emptyA2 = "{ return new EmptyTag(\"" + tag + "\"); }";
            // Attr shorthands
            String emptyB1 = "public static EmptyTag " + tag + "(Attr.ShortForm shortAttr)";
            String emptyB2 = "{ return Attr.addTo(new EmptyTag(\"" + tag + "\"), shortAttr); }";
            // Print
            System.out.println(String.format("%-80s%1s", emptyA1, emptyA2));
            System.out.println(String.format("%-80s%1s", emptyB1, emptyB2));
            System.out.println("");
        }
        System.out.println("// ContainerTags, generated in " + TagCreatorCodeGenerator.class);
        for (String tag : containerTags()) {
            String containerA1 = "public static ContainerTag " + tag + "()";
            String containerA2 = "{ return new ContainerTag(\"" + tag + "\"); }";
            String containerB1 = "public static ContainerTag " + tag + "(String text)";
            String containerB2 = "{ return new ContainerTag(\"" + tag + "\").withText(text); }";
            String containerC1 = "public static ContainerTag " + tag + "(DomContent... dc)";
            String containerC2 = "{ return new ContainerTag(\"" + tag + "\").with(dc); }";
            // Attr shorthands
            String containerD1 = "public static ContainerTag " + tag + "(Attr.ShortForm shortAttr)";
            String containerD2 = "{ return Attr.addTo(new ContainerTag(\"" + tag + "\"), shortAttr); }";
            String containerE1 = "public static ContainerTag " + tag + "(Attr.ShortForm shortAttr, String text)";
            String containerE2 = "{ return Attr.addTo(new ContainerTag(\"" + tag + "\").withText(text), shortAttr); }";
            String containerF1 = "public static ContainerTag " + tag + "(Attr.ShortForm shortAttr, DomContent... dc)";
            String containerF2 = "{ return Attr.addTo(new ContainerTag(\"" + tag + "\").with(dc), shortAttr); }";
            // Print
            System.out.println(String.format("%-80s%1s", containerA1, containerA2));
            System.out.println(String.format("%-80s%1s", containerB1, containerB2));
            System.out.println(String.format("%-80s%1s", containerC1, containerC2));
            System.out.println(String.format("%-80s%1s", containerD1, containerD2));
            System.out.println(String.format("%-80s%1s", containerE1, containerE2));
            System.out.println(String.format("%-80s%1s", containerF1, containerF2));
            System.out.println("");
        }
    }

    //  This is a method that contains all ContainerTags, there is nothing below it
    private static List<String> emptyTags() {
        return Arrays.asList(
            "area",
            "base",
            "br",
            "col",
            //"!DOCTYPE html",
            "embed",
            "hr",
            "img",
            "input",
            "keygen",
            "link",
            "meta",
            "param",
            "source",
            "track",
            "wbr"
        );
    }

    private static List<String> containerTags() {
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
            "header",
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
