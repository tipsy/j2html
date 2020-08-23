package j2html.tags.generators;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static j2html.tags.generators.AttributesList.attributes;

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
        //IMPORTANT: '_' added as suffix to mitigate problems
        //where attributes are java keywords. Just to make it consistent and avoid special cases.
        final String attrName = interfaceNameSimple.toLowerCase();
        final String paramName = attrName+"_";
        sb.append("default ")
            .append("T ").append("with").append(interfaceNameSimple)
            .append("(final String ").append(paramName).append(") {")

            .append("get().attr(\"").append(attrName).append("\", "+paramName+");\n")
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

}
