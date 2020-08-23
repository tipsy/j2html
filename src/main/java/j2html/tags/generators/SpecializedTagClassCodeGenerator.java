package j2html.tags.generators;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
        final String tag
    ){

        final StringBuilder sb = new StringBuilder();

        sb.append(getPackage());
        sb.append("\n");

        for(String importName : imports){
            sb.append("import ").append(importName).append(";\n");
        }
        sb.append("\n");
        sb.append("public final class ")
            .append(className);

        if(optExtends.isPresent()) {
                sb.append(" extends ").append(optExtends.get())
                .append(" ");
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

    public static void mainInner(final boolean delete) throws IOException {
        System.out.println("// EmptyTags, generated in " + SpecializedTagClassCodeGenerator.class);

        for (final String tag : emptyTags()) {
            final String className = classNameFromTag(tag);
            final Path path = makePath(tag);

            final String classString =
              getClassTemplate(
                  className,
                  Optional.of("EmptyTag<"+className+">"),
                  Arrays.asList(
                      "j2html.tags.EmptyTag",
                      "j2html.tags.attributes.*"
                      ),
                  tag
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
            final String classString =
                getClassTemplate(
                    className,
                    Optional.of("ContainerTag<"+className+">"),
                    Arrays.asList("j2html.tags.ContainerTag"),
                    tag
                );

            if(delete){
                Files.delete(path);
            }else {
                Files.write(path, classString.getBytes());
            }
        }
    }
}
