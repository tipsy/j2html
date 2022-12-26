package com.j2html.codegen;

import com.j2html.codegen.Model.Node;
import com.squareup.javapoet.*;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static com.j2html.codegen.Model.Metadata.ON_OFF;
import static com.j2html.codegen.Model.Metadata.SELF_CLOSING;

public class Generator {

    public static final ClassName INSTANCE = ClassName.get("j2html.tags", "IInstance");
    public static final ClassName TAG = ClassName.get("j2html.tags", "Tag");
    public static final ClassName EMPTY_TAG = ClassName.get("j2html.tags", "EmptyTag");
    public static final ClassName CONTAINER_TAG = ClassName.get("j2html.tags", "ContainerTag");

    public static void main(String... args) throws IOException {
        Path path = Paths.get("j2html-codegen", "src", "test", "resources", "html.model");
        String definitions = new String(Files.readAllBytes(path));
        Model model = new Model();
        Parser.parse(definitions, model);

        Path dir = Paths.get("/j2html/generated-source");
        Files.createDirectories(dir);
        generate(dir, "j2html.tags.attributes", "j2html.tags.specialized", model);
    }

    public static void generate(Path root, String attributePkg, String elementPkg, Model model) throws IOException {
        Map<String, JavaFile> attributes = generateAttributePackage(attributePkg, model);
        for (JavaFile file : attributes.values()) {
            file.writeTo(root);
        }

        Map<String, JavaFile> elements = generateElementPackage(elementPkg, model, attributes);
        for (JavaFile file : elements.values()) {
            file.writeTo(root);
        }
    }

    private static Map<String, JavaFile> generateElementPackage(String pkg, Model model, Map<String, JavaFile> attributes) {
        Map<String, JavaFile> files = new HashMap<>();

        // Convert all elements into classes.
        for (Node element : model.elements()) {
            ClassName className = ClassName.get(pkg, capitalize(element.name) + "Tag");

            TypeSpec.Builder type = defineElementClass(element, className);

            // Assign attributes to this element.
            for (Node attribute : element.children) {
                JavaFile file = attributes.get(attribute.name);
                type.addSuperinterface(
                    ParameterizedTypeName.get(
                        ClassName.get(file.packageName, file.typeSpec.name),
                        className
                    )
                );
            }

            files.put(
                element.name,
                JavaFile.builder(pkg, type.build())
                    .skipJavaLangImports(true)
                    .build()
            );
        }

        return files;
    }

    private static Map<String, JavaFile> generateAttributePackage(String pkg, Model model) {
        Map<String, JavaFile> files = new HashMap<>();

        // Convert all attributes into classes.
        for (Node attribute : model.attributes()) {
            TypeSpec.Builder type = defineAttributeClass(pkg, attribute);

            if (attribute.type.equals(Node.Type.STRING)) {
                defineStringAttributeMethods(attribute, type);
            } else if (attribute.type.equals(Node.Type.BOOLEAN) && !attribute.is(ON_OFF)) {
                defineBooleanAttributeMethods(attribute, type);
            } else if (attribute.type.equals(Node.Type.BOOLEAN) && attribute.is(ON_OFF)) {
                defineOnOffAttributeMethods(attribute, type);
            }

            files.put(
                attribute.name,
                JavaFile.builder(pkg, type.build())
                    .skipJavaLangImports(true)
                    .build()
            );
        }

        return files;
    }

    private static TypeSpec.Builder defineElementClass(Node element, ClassName className) {
        MethodSpec constructor = MethodSpec.constructorBuilder()
            .addModifiers(Modifier.PUBLIC)
            .addStatement("super(\"" + element.name + "\")")
            .build();

        TypeSpec.Builder type = TypeSpec.classBuilder(className)
            .addModifiers(Modifier.PUBLIC)
            .superclass(
                ParameterizedTypeName.get(element.is(SELF_CLOSING) ? EMPTY_TAG : CONTAINER_TAG, className)
            )
            .addMethod(constructor);
        return type;
    }

    private static TypeSpec.Builder defineAttributeClass(String pkg, Node attribute) {
        ClassName name = ClassName.get(pkg, "I" + capitalize(attribute.name));
        return TypeSpec.interfaceBuilder(name)
            .addSuperinterface(ParameterizedTypeName.get(INSTANCE, TypeVariableName.get("T")))
            .addTypeVariable(TypeVariableName.get("T", ParameterizedTypeName.get(TAG, TypeVariableName.get("T"))))
            .addModifiers(Modifier.PUBLIC);
    }

    private static void defineBooleanAttributeMethods(Node attribute, TypeSpec.Builder type) {
        MethodSpec with = MethodSpec.methodBuilder(methodName("is", attribute.name))
            .addModifiers(Modifier.PUBLIC, Modifier.DEFAULT)
            .addStatement("return self().attr(\"" + attribute.name + "\")")
            .returns(TypeVariableName.get("T"))
            .build();

        MethodSpec withCond = MethodSpec.methodBuilder(methodName("withCond", attribute.name))
            .addModifiers(Modifier.PUBLIC, Modifier.DEFAULT)
            .addParameter(TypeName.BOOLEAN, "enable", Modifier.FINAL)
            .addStatement("return enable ? self().attr(\"" + attribute.name + "\") : self()")
            .returns(TypeVariableName.get("T"))
            .build();

        type.addMethod(with);
        type.addMethod(withCond);
    }

    private static void defineOnOffAttributeMethods(Node attribute, TypeSpec.Builder type) {
        MethodSpec with = MethodSpec.methodBuilder(methodName("is", attribute.name))
            .addModifiers(Modifier.PUBLIC, Modifier.DEFAULT)
            .addStatement("return self().attr(\"" + attribute.name + "\", \"on\")")
            .returns(TypeVariableName.get("T"))
            .build();

        MethodSpec withCond = MethodSpec.methodBuilder(methodName("withCond", attribute.name))
            .addModifiers(Modifier.PUBLIC, Modifier.DEFAULT)
            .addParameter(TypeName.BOOLEAN, "enable", Modifier.FINAL)
            .addStatement("return enable ? self().attr(\"" + attribute.name + "\", \"on\") : self()")
            .returns(TypeVariableName.get("T"))
            .build();

        type.addMethod(with);
        type.addMethod(withCond);
    }

    private static void defineStringAttributeMethods(Node attribute, TypeSpec.Builder type) {
        MethodSpec with = MethodSpec.methodBuilder(methodName("with", attribute.name))
            .addModifiers(Modifier.PUBLIC, Modifier.DEFAULT)
            .addParameter(String.class, parameter(attribute), Modifier.FINAL)
            .addStatement("return self().attr(\"" + attribute.name + "\", " + parameter(attribute) + ")")
            .returns(TypeVariableName.get("T"))
            .build();

        MethodSpec withCond = MethodSpec.methodBuilder(methodName("withCond", attribute.name))
            .addModifiers(Modifier.PUBLIC, Modifier.DEFAULT)
            .addParameter(TypeName.BOOLEAN, "enable", Modifier.FINAL)
            .addParameter(String.class, parameter(attribute), Modifier.FINAL)
            .addStatement("return enable ? self().attr(\"" + attribute.name + "\", " + parameter(attribute) + ") : self()")
            .returns(TypeVariableName.get("T"))
            .build();

        type.addMethod(with);
        type.addMethod(withCond);
    }

    private static String parameter(Node attribute) {
        return attribute.name + "_";
    }

    private static String methodName(String... words) {
        String[] camelCase = new String[words.length];
        camelCase[0] = words[0];
        for (int i = 1; i < words.length; i++) {
            camelCase[i] = capitalize(words[i]);
        }
        return String.join("", camelCase);
    }

    private static String capitalize(String word) {
        return word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
    }
}
