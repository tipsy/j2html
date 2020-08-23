package j2html.tags.generators;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public final class AttributeInterfaceCodeGenerator {

    public static void main(String[] args){
        try {
            final boolean delete = false;

            for (final String attr : attributes()) {
                final Path path = makePath(attr);
                final String interfaceName = interfaceNameFromAttribute(attr)+"<T extends Tag>";

                /*
                IFormAction<T extends Tag> extends IInstance<T>

                default T withFormAction(String formAction){
                    get().attr("formaction", formAction);
                    return get();
                }
                 */

                final String interfaceStr = getInterfaceTemplate(
                    interfaceName,
                    Optional.of("IInstance<T>"),
                    Arrays.asList("j2html.tags.Tag"),
                    interfaceNameFromAttribute(attr).substring(1)
                );

                if (delete) {
                    Files.delete(path);
                }else{
                    Files.write(path, interfaceStr.getBytes());
                }
            }
        }catch (Exception ignored){}
    }

    private static String getPackage(){
        return "package j2html.tags.attributes;\n";
    }

    private static String getInterfaceTemplate(
        final String interfaceName,
        final Optional<String> optExtends,
        final List<String> imports,
        final String interfaceNameSimple
    ){

        final StringBuilder sb = new StringBuilder();

        sb.append(getPackage());
        sb.append("\n");

        for(String importName : imports){
            sb.append("import ").append(importName).append(";\n");
        }
        sb.append("\n");
        sb.append("public interface ")
            .append(interfaceName);

        if(optExtends.isPresent()) {
            sb.append(" extends ").append(optExtends.get())
                .append(" ");
        }

        sb.append(" {\n");

        //interface contents
        /*
        IFormAction<T extends Tag> extends IInstance<T>

        default T withFormAction(String formAction){
            get().attr("formaction", formAction);
            return get();
        }
         */
        final String paramName = interfaceNameSimple.toLowerCase();
        sb.append("default ")
            .append("T ").append("with").append(interfaceNameSimple)
            .append("(final String ").append(paramName).append(") {")

            .append("get().attr(\"").append(paramName).append("\", "+paramName+");\n")
            .append("return get();\n")

            .append("}\n");

        sb.append("}\n");

        return sb.toString();
    }

    private static String interfaceNameFromAttribute(String tageNameLowerCase){
        String res = tageNameLowerCase.substring(0,1).toUpperCase()+tageNameLowerCase.substring(1);
        return "I" + res;
    }

    private static Path makePath(String tagLowerCase){
        final String filename = interfaceNameFromAttribute(tagLowerCase)+".java";
        return Paths.get("src/main/java/j2html/tags/attributes/"+filename);
    }

    private static List<String> attributes() {
        return Arrays.asList(
            //"accept", implemented manually
            //"accept-charset", contains dashes, TODO
            "accesskey",
            "action",
            //"align", not supported in HTML5
            "alt",
            "async",
            "autocomplete",
            "autofocus",
            "autoplay",
            //"bgcolor", not supported in HTMTL5
            //"border", not supported in HTML5
            "charset",
            "checked",
            "cite",
            //"class" already implemented in Tag.java
            "cols",
            "colspan",
            "content",
            //"contenteditable" global attribute, should be in Tag.java
            "controls",
            "coords",
            "data",
            "datetime",
            "default",
            "defer",
            "dir",
            "dirname",
            //"disabled", manually implemented
            //"download" manually implemented
            //"draggable" global attribute, should be in Tag.java
            "enctype",
            "for",
            //"form" manually implemented,
            //"formaction" manually implemented
            "headers",
            "height",
            "hidden",
            "high",
            "href",
            "hreflang",
            "http-equiv",
            //"id" global attribute, should be in Tag.java
            "ismap",
            "kind",
            "label",
            //"lang" global attribute, should be in Tag.java
            "list",
            "loop",
            "low",
            "max",
            "maxlength",
            "media",
            "method",
            "min",
            "multiple",
            "muted",
            "name",
            "novalidate",
            "onabort",
            "onafterprint",
            "onbeforeprint",
            "onbeforeunload",
            "onblur",
            "oncanplay",
            "oncanplaythrough",
            /* a bunch of event attributes that are on all visible elements (so should be in Tag.java)
            "onchange",
            ""

             */
            "ondurationchange",
            "onemptied",
            "onended",
            "onerror",
            //"onfocus" global attribute
            "onhashchange",
            // ... a bunch of event attributes visible on all elements
            "onload",
            "onloadeddata",
            "onloadedmetadata",
            "onloadstart",
            // ... a bunch of event attributes visible on all elements
            "onoffline",
            "ononline",
            "onpagehide",
            "onpageshow",
            //"onpaste" global attribute
            "onpause",
            "onplay",
            "onplaying",
            "onpopstate",
            "onprogress",
            "onratechange",
            "onreset",
            "onresize",
            "onscroll",
            "onsearch",
            "onseeked",
            "onseeking",
            "onselect",
            "onstalled",
            "onstorage",
            "onsubmit",
            "onsuspend",
            "ontimeupdate",
            "ontoggle",
            "onunload",
            "onvolumechanged",
            "onwaiting",
            "onwheel",
            "open",
            "optimum",
            "pattern",
            "placeholder",
            "poster",
            "preload",
            "readonly",
            "rel",
            "required",
            "reversed",
            "rows",
            "rowspan",
            "sandbox",
            "scope",
            "selected",
            "shape",
            "size",
            "sizes",
            "span",
            "spellcheck",
            "src",
            "srcdoc",
            "srclang",
            "srcset",
            "start",
            "step",
            //"style", global attribute
            //"tabindex", global attribute
            "target",
            //"title", global attribute
            //"translate" global attribute
            "type",
            "usemap",
            "value",
            "width",
            "wrap"
        );
    }
}
