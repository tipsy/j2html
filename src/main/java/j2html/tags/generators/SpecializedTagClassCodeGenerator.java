package j2html.tags.generators;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static j2html.tags.generators.TagCreatorCodeGenerator.containerTags;
import static j2html.tags.generators.TagCreatorCodeGenerator.emptyTags;

class SpecializedTagClassCodeGenerator {

    public static void main(String[] args) {
        try{
            //the delete argument serves to give the possibility
            //to delete the classes that were written before
            mainInner(false);
        }catch (Exception ignored){}
    }
    public static String classNameFromTag(String tageNameLowerCase){
        String res = tageNameLowerCase.substring(0,1).toUpperCase()+tageNameLowerCase.substring(1);
        return res + "Tag";
    }

    private static Path makePath(String tagLowerCase){
        final String filename = classNameFromTag(tagLowerCase)+".java";
        return Paths.get("src/main/java/j2html/tags/specialized/"+filename);
    }

    private static String getPackage(){
        return "package j2html.tags.specialized;\n";
    }

    private static String getClassTemplate(
        final String className,
        final Optional<String> optExtends,
        final List<String> imports,
        final String tag,
        final List<String> interfaces
    ){

        final StringBuilder sb = new StringBuilder();

        sb.append(getPackage());
        sb.append("\n");

        for(String importName : imports){
            sb.append("import ").append(importName).append(";\n");
        }
        sb.append("\n");
        sb.append("public final class ")
            .append(className)
            .append(" ");

        if(optExtends.isPresent()) {
                sb.append("extends ").append(optExtends.get())
                .append(" ");
        }

        //add the 'implements' clause
        if(!interfaces.isEmpty()) {
            sb.append("\n");
            sb.append("implements ");

            final List<String> genericInterfaceNames
                = interfaces.stream().map(iName -> iName+"<"+className+">")
                .collect(Collectors.toList());
            sb.append(
                String.join(",", genericInterfaceNames)
            );
        }

        sb.append(" {\n");

        //class contents
        sb.append("public ")
            .append(className)
            .append("() {")
            .append("super(\"").append(tag).append("\");")
            .append("}\n");

        sb.append("}\n");

        return sb.toString();
    }

    private static List<String> getInterfaceNamesForTag(final String tagNameLowercase){
        return AttributesList.getCustomAttributesForHtmlTag(tagNameLowercase)
            .stream()
            .map(
                AttributeInterfaceCodeGenerator::interfaceNameFromAttribute
            ).collect(Collectors.toList());
    }

    public static void mainInner(final boolean delete) throws IOException {
        System.out.println("// EmptyTags, generated in " + SpecializedTagClassCodeGenerator.class);

        for (final String tag : emptyTags()) {
            final String className = classNameFromTag(tag);
            final Path path = makePath(tag);

            final List<String> interfaceNames = getInterfaceNamesForTag(tag);

            final String classString =
              getClassTemplate(
                  className,
                  Optional.of("EmptyTag<"+className+">"),
                  Arrays.asList(
                      "j2html.tags.EmptyTag",
                      "j2html.tags.attributes.*"
                      ),
                  tag,
                  interfaceNames
              );

            /*
            public InputTag() {
                super("input");
            }
            */

            if(delete){
                Files.delete(path);
            }else {
                Files.write(path, classString.getBytes());
            }
        }

        System.out.println("// ContainerTags, generated in " + SpecializedTagClassCodeGenerator.class);

        for (final String tag : containerTags()) {
            final Path path = makePath(tag);
            final String className = classNameFromTag(tag);

            final List<String> interfaceNames = getInterfaceNamesForTag(tag);

            final String classString =
                getClassTemplate(
                    className,
                    Optional.of("ContainerTag<"+className+">"),
                    Arrays.asList(
                        "j2html.tags.ContainerTag",
                        "j2html.tags.attributes.*"
                    ),
                    tag,
                    interfaceNames
                );

            if(delete){
                Files.delete(path);
            }else {
                Files.write(path, classString.getBytes());
            }
        }
    }
}
