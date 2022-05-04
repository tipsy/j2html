package j2html_codegen;

import j2html_codegen.generators.TagCreatorCodeGenerator;
import j2html_codegen.wattsi.ElementDefinition;
import j2html_codegen.wattsi.WattsiSource;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;

public class CodeGeneratorComplianceTests {

    private WattsiSource specification;

    @Before
    public void setUp() throws IOException {
        Path source = Paths.get("src","test","resources","2022-01.wattsi");
        Document doc = Jsoup.parse(source.toFile(), "UTF-8", "https://html.spec.whatwg.org/");
        specification = new WattsiSource(doc);
    }

    private Set<String> generatedElements(){
        Set<String> elements = new HashSet<>();
        elements.addAll(TagCreatorCodeGenerator.emptyTags());
        elements.addAll(TagCreatorCodeGenerator.containerTags());
        return elements;
    }

    private Set<String> specifiedElements(WattsiSource source){
        Set<String> elements = new HashSet<>();
        for(ElementDefinition element : source.elementDefinitions()){
            elements.add(element.name());
        }
        return elements;
    }

    @Test
    public void all_wattsi_elements_are_defined_in_the_code_generator() {
        Set<String> generated = generatedElements();

        List<String> undefined = specification.elementDefinitions().stream()
            .filter(element -> !element.isObsolete())
            .filter(element -> !generated.contains(element.name()))
            .map(ElementDefinition::name)
            .collect(toList());

        assertEquals("HTML elements are missing", emptyList(), undefined);
        // Currently missing (and mostly deprecated):
        //   hgroup, data, template, slot
    }

    @Test
    public void only_wattsi_elements_are_defined_in_the_code_generator(){
        Set<String> specified = specifiedElements(specification);

        List<String> invalid = generatedElements().stream()
            .filter(element -> !specified.contains(element))
            .collect(toList());

        assertEquals("HTML elements are invalid", emptyList(), invalid);
    }

}
