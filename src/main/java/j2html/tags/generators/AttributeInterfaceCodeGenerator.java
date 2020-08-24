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

            for (final AttrD attr : AttributesList.attributesDescriptive()) {
                final Path path = makePath(attr.attr);
                final String interfaceName = interfaceNameFromAttribute(attr.attr)+"<T extends Tag>";

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
                    interfaceNameFromAttribute(attr.attr).substring(1),
                    attr
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

    private static String makeReturnTypeAndMethodName(final String name){
        return "default "+ "T "+name;
    }

    private static String getInterfaceTemplate(
        final String interfaceName,
        final Optional<String> optExtends,
        final List<String> imports,
        final String interfaceNameSimple,
        final AttrD attrD
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

        //depending on if the attribute has an argument or not,
        //generate methods according to the convention in Tag.java
        // arg -> with$ATTR(arg), withCond$ATTR(condition, arg)
        // no arg -> is$ATTR(), withCond$ATTR(condition)

        //append the 'with$ATTR' method
        writeAttributeMethod(interfaceNameSimple, attrD, sb, attrName, paramName);
        writeAttributeMethodCond(interfaceNameSimple, attrD, sb, attrName, paramName);

        sb.append("}\n");

        return sb.toString();
    }

    private static void addAttributeNoArg(final StringBuilder sb, final String attrName){
        //generate the code to add an attribute without an argument

        //there are some special attributes
        //which do take an argument, but where the argument
        //is boolean (meaning on/off, yes/no and the like)
        sb.append("get().attr(\"");
        if(attrName.equals("autocomplete")){
            sb.append(attrName).append("\",\"on\"");
        }else {

            sb.append(attrName).append("\"");
        }
        sb.append(");\n");
    }

    private static void writeAttributeMethodCond(String interfaceNameSimple, AttrD attrD, StringBuilder sb, String attrName, String paramName) {

        sb.append(makeReturnTypeAndMethodName("withCond"+interfaceNameSimple));

        if(attrD.hasArgument){
            //add a variant where you can specify the argument

            sb.append("(final boolean enable, final String ").append(paramName).append(") {");

                sb.append("if (enable){\n");
                    sb.append("get().attr(\"").append(attrName).append("\", "+ paramName +");\n");
                sb.append("}\n");

                sb.append("return get();\n");
        }else{
            //add a variant where you can toggle the attribute

            sb.append("(final boolean enable) {");
                sb.append("if (enable){\n");
                    addAttributeNoArg(sb, attrName);
                sb.append("}\n");
                sb.append("return get();\n");
        }
        sb.append("}\n");
    }

    private static void writeAttributeMethod(String interfaceNameSimple, AttrD attrD, StringBuilder sb, String attrName, String paramName) {

        sb.append(makeReturnTypeAndMethodName(
            ((attrD.hasArgument)?"with":"is")+interfaceNameSimple)
        );

        if(attrD.hasArgument){
            //add a variant where you can specify the argument

            sb.append("(final String ").append(paramName).append(") {")

                .append("get().attr(\"").append(attrName).append("\", "+ paramName +");\n")
                .append("return get();\n");
        }else{
            //add a variant where you can toggle the attribute

            sb.append("() {");

                addAttributeNoArg(sb, attrName);

                sb.append("return get();\n");
        }
        sb.append("}\n");
    }

    public static String interfaceNameFromAttribute(String attribute){
        String res = attribute.substring(0,1).toUpperCase()+attribute.substring(1);
        return "I" + res;
    }

    private static Path makePath(String tagLowerCase){
        final String filename = interfaceNameFromAttribute(tagLowerCase)+".java";
        return Paths.get("src/main/java/j2html/tags/attributes/"+filename);
    }

}
