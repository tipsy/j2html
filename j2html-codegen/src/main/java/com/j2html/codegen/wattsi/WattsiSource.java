package com.j2html.codegen.wattsi;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class WattsiSource {

    private final Document doc;

    private final Set<Reference> obsolete = new HashSet<>();

    public WattsiSource(Document doc) {
        this.doc = doc;

        // Find where obsolete elements are defined or referenced.
        Elements obsoleteElements = doc.select("p:contains(Elements in the following list are entirely obsolete) + dl");

        // Convert definitions into references to record obsolete elements.
        obsoleteElements.select("dt > dfn[element]")
            .stream()
            .map(WattsiElement::new)
            .map(WattsiElement::reference)
            .forEach(obsolete::add);

        // Extract references to record obsolete elements.
        obsoleteElements.select("dt > code")
            .stream()
            .map(Element::childNodes)
            .map(Reference::from)
            .forEach(obsolete::add);

        // Find where obsolete attributes are defined or referenced.
        Elements obsoleteAttributes = doc.select("p:contains(The following attributes are obsolete) + dl");

        // Convert definitions into references to record obsolete attributes.
        obsoleteAttributes.select("dt > dfn[element-attr]").stream()
            .map(WattsiAttribute::new)
            .map(WattsiAttribute::reference)
            .forEach(obsolete::add);

//        System.out.println(obsoleteAttributes.select("dt"));

//        obsoleteAttributes.select("dt > code").stream()
//            .map(Element::childNodes)
//            .map(Reference::from)
//            .forEach(System.err::println);

//        System.out.println(
//            doc.select("dfn[obsolete]")
//        );
    }

    public List<ElementDefinition> elementDefinitions() {
        return doc.select("dfn[element]").stream()
            .map(WattsiElement::new)
            .collect(toList());
    }

    public List<AttributeDefinition> attributeDefinitions() {
        return doc.select("dfn[element-attr]").stream()
            .map(WattsiAttribute::new)
            .collect(toList());
    }

    public class WattsiElement implements ElementDefinition {
        private final Element dfn;

        WattsiElement(Element dfn) {
            if (!"dfn".equals(dfn.tagName())) {
                throw new IllegalArgumentException("Element cannot be defined from: " + dfn);
            }
            if (!dfn.hasAttr("element")) {
                throw new IllegalArgumentException("Does not define an element: " + dfn);
            }
            if (dfn.childrenSize() != 1) {
                throw new IllegalArgumentException("Element cannot have multiple definitions: " + dfn);
            }
            this.dfn = dfn;
        }

        private Reference reference() {
            return Reference.from(dfn.childNodes());
        }

        @Override
        public String name() {
            if (dfn.hasAttr("data-x")) {
                return dfn.attr("data-x");
            }

            return Reference.from(dfn.childNodes()).key;
        }

        @Override
        public boolean isSelfClosing() {
            return false;
        }

        @Override
        public boolean isObsolete() {
            return obsolete.contains(reference());
        }
    }

    public class WattsiAttribute implements AttributeDefinition {
        private final Element dfn;

        WattsiAttribute(Element dfn) {
            if (!"dfn".equals(dfn.tagName())) {
                throw new IllegalArgumentException("Attribute cannot be defined from: " + dfn);
            }
            if (!dfn.hasAttr("element-attr")) {
                throw new IllegalArgumentException("Does not define an attribute: " + dfn);
            }
            if (dfn.childrenSize() != 1) {
                throw new IllegalArgumentException("Attribute cannot have multiple definitions: " + dfn);
            }

            this.dfn = dfn;
        }

        private Reference reference() {
            return Reference.from(dfn.childNodes());
        }

        @Override
        public String name() {
            return reference().text;
        }

        private List<String> targets() {
            if (dfn.hasAttr("for")) {
                return Arrays.asList(dfn.attr("for").trim().split(","));
            }
            return new ArrayList<>();
        }

        @Override
        public boolean appliesTo(ElementDefinition element) {
            return targets().contains(element.name());
        }

        @Override
        public boolean isObsolete() {
            return obsolete.contains(reference());
        }
    }

    private static class Reference {
        private final String key;
        private final String text;

        Reference(String key, String text) {
            this.key = key;
            this.text = text;
        }

        @Override
        public String toString() {
            return key + "[" + text + "]";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Reference reference = (Reference) o;
            return key.equals(reference.key);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key);
        }

        public static Reference from(List<Node> nodes) {
            if (nodes.stream().allMatch(n -> n instanceof TextNode)) {
                String txt = nodes.stream()
                    .map(n -> (TextNode) n)
                    .map(TextNode::text)
                    .collect(Collectors.joining(" "));
                return new Reference(txt, txt);
            }

            for (Node node : nodes) {
                if (node instanceof Element) {
                    Element element = (Element) node;
                    if (element.is("code") || element.is("span")) {
                        if (element.hasAttr("data-x")) {
                            return new Reference(element.attr("data-x").toLowerCase(), element.text());
                        } else {
                            return new Reference(element.text().toLowerCase(), element.text());
                        }
                    }
                }
            }

            return null;
        }
    }
}
