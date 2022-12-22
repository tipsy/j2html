package com.j2html.codegen;

import java.util.*;

import static com.j2html.codegen.Model.Metadata.ON_OFF;
import static com.j2html.codegen.Model.Metadata.SELF_CLOSING;
import static com.j2html.codegen.Model.Node.Type.*;

public class Model implements Parser.Listener {

    private Map<String, Node> elements;
    private Map<String, Node> attributes;

    public Model() {
        elements = new LinkedHashMap<>();
        attributes = new LinkedHashMap<>();
    }

    public Collection<Node> elements(){
        return elements.values();
    }

    public Collection<Node> attributes(){
        return attributes.values();
    }

    public Node addElement(String name) {
        return add(ELEMENT, name, elements);
    }

    public Node addBooleanAttribute(String name) {
        return add(BOOLEAN, name, attributes);
    }

    public Node addStringAttribute(String name) {
        return add(STRING, name, attributes);
    }

    public Node element(String name) {
        if (!elements.containsKey(name)) {
            throw new NodeDoesNotExist(name);
        }
        return elements.get(name);
    }

    public Node attribute(String name) {
        if (!attributes.containsKey(name)) {
            throw new NodeDoesNotExist(name);
        }
        return attributes.get(name);
    }

    private Node add(Node.Type type, String name, Map<String, Node> nodes) {
        if (nodes.containsKey(name)) {
            throw new NodeAlreadyExists(name);
        }

        Node node = new Node(type, name);
        nodes.put(name, node);
        return node;
    }

    @Override
    public void lineCommented(int line, String txt) {
        // Ignore.
    }

    @Override
    public void elementDefined(int line, String name) {
        attempt(() -> addElement(name), line);
    }

    @Override
    public void emptyElementDefined(int line, String name) {
        attempt(() -> addElement(name).annotate(SELF_CLOSING), line);
    }

    @Override
    public void booleanDefined(int line, String name) {
        attempt(() -> addBooleanAttribute(name), line);
    }

    @Override
    public void onOffDefined(int line, String name) {
        attempt(() -> addBooleanAttribute(name).annotate(ON_OFF), line);
    }

    @Override
    public void stringDefined(int line, String name) {
        attempt(() -> addStringAttribute(name), line);
    }

    @Override
    public void attributeDefined(int line, String element, String name) {
        attempt(() -> element(element).addChild(attribute(name)), line);
    }

    @Override
    public void invalidLine(int line, String txt) {
        throw new RuntimeException("Invalid line [" + line + "]: " + txt);
    }

    @FunctionalInterface
    private interface Unsafe {
        void call() throws RuntimeException;
    }

    private void attempt(Unsafe operation, int line) {
        try {
            operation.call();
        } catch (RuntimeException e) {
            throw new InvalidModel(e, line);
        }
    }

    public static class Node {
        enum Type {
            ELEMENT,
            BOOLEAN,
            STRING
        }

        public final Type type;
        public final String name;
        public final List<Metadata> metadata;
        public final List<Node> children;

        private Node(Type type, String name) {
            this.type = type;
            this.name = name;
            this.metadata = new ArrayList<>();
            this.children = new ArrayList<>();
        }

        public void annotate(Metadata meta) {
            metadata.add(meta);
        }

        public void addChild(Node node) {
            children.add(node);
        }

        public boolean is(Metadata annotation){
            return metadata.contains(annotation);
        }
    }

    public enum Metadata {
        SELF_CLOSING,
        ON_OFF,
        OBSOLETE
    }

    public static class InvalidModel extends RuntimeException {
        public InvalidModel(Exception cause, int line) {
            super(cause.getMessage() + ".  At line " + line, cause);
        }
    }

    public static class NodeAlreadyExists extends RuntimeException {
        public NodeAlreadyExists(String name) {
            super("Node already exists: " + name);
        }
    }

    public static class NodeDoesNotExist extends RuntimeException {
        public NodeDoesNotExist(String name) {
            super("Node does not exist: " + name);
        }
    }
}
