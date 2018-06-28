package j2html.tags;

import com.google.gson.*;
import com.squareup.javapoet.*;
import j2html.attributes.Attr;

import javax.lang.model.element.Modifier;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;

import static org.apache.commons.lang3.StringUtils.capitalize;

public class TagCreatorCodeGeneratorFromJson {

    public static void main(String[] args) throws Exception {
        StringWriter output = new StringWriter();

        try (FileReader fileReader = new FileReader("src/main/resources/tagClasses.json")) {
            JsonObject json = new Gson().fromJson(fileReader, JsonObject.class);
            ClassName containerTagClassName = ClassName.get(ContainerTag.class);
            json.getAsJsonArray("tags").forEach(element -> {
                JsonObject tagJson = element.getAsJsonObject();

                String tag = tagJson.get("tag").getAsString();
                ClassName tagClassName = ClassName.get("j2html.tags", capitalize(tag + "Tag"));
                TypeSpec.Builder typeSpec = TypeSpec.classBuilder(tagClassName)
                    .superclass(ParameterizedTypeName.get(containerTagClassName, tagClassName))
                    .addMethod(
                        MethodSpec.constructorBuilder()
                            .addModifiers(Modifier.PUBLIC)
                            .addStatement("super(\"" + tag + "\")")
                            .build());

                JsonArray attrs = tagJson.getAsJsonArray("attrs");
                attrs.forEach(attrElement -> {
                    String attrName = attrElement.isJsonObject() ? attrElement.getAsJsonObject().get("name").getAsString() : attrElement.getAsString();
                    String attrNameCapitalized = capitalize(attrName);

                    MethodSpec.Builder methodSpec = MethodSpec.methodBuilder("with" + attrNameCapitalized)
                        .addModifiers(Modifier.PUBLIC)
                        .returns(tagClassName);

                    MethodSpec.Builder condMethodSpec = MethodSpec.methodBuilder("withCond" + attrNameCapitalized)
                        .addModifiers(Modifier.PUBLIC)
                        .addParameter(boolean.class, "condition")
                        .returns(tagClassName);

                    if (attrElement.isJsonObject() && new JsonPrimitive(true).equals(attrElement.getAsJsonObject().get("empty"))) {
                        methodSpec.addStatement("return attr(\"" + attrName + "\")");
                        condMethodSpec.addStatement("return condAttr(condition, \"" + attrName + "\", \"" + attrName + "\")");
                    } else {
                        methodSpec
                            .addParameter(String.class, attrName)
                            .addStatement("return attr(\"" + attrName + "\", " + attrName + ")");
                        condMethodSpec
                            .addParameter(boolean.class, "condition")
                            .addParameter(String.class, attrName)
                            .addStatement("return condAttr(condition, \"" + attrName + "\", " + attrName + ")");
                    }
                    typeSpec.addMethod(methodSpec.build())
                        .addMethod(condMethodSpec.build());
                });

                try {
                    JavaFile.builder("j2html.tags", typeSpec.build())
                        .build()
                        .writeTo(output);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        System.out.println(output);
    }
}
