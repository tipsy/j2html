package com.j2html.codegen;

import org.junit.Test;
import org.mockito.InOrder;

import java.util.function.Consumer;

import static org.mockito.Mockito.*;

public class ParserTest {

    private void verifyParsing(String txt, Consumer<Parser.Listener> checks) {
        Parser.Listener listener = mock(Parser.Listener.class);
        Parser.parse(txt, listener);
        checks.accept(listener);
    }

    @Test
    public void an_empty_input_has_no_events() {
        verifyParsing("", listener -> {
            verifyNoInteractions(listener);
        });
    }

    @Test
    public void whitespace_has_no_events() {
        verifyParsing("    \t\t\t\t", listener -> {
            verifyNoInteractions(listener);
        });
    }

    @Test
    public void commented_lines_are_signaled() {
        verifyParsing("#Comment 1.\n# Comment B?", listener -> {
            InOrder order = inOrder(listener);
            order.verify(listener).lineCommented(1, "#Comment 1.");
            order.verify(listener).lineCommented(2, "# Comment B?");
        });
    }

    @Test
    public void node_definitions_are_signaled() {
        verifyParsing("ELEMENT[a]\nEMPTY-ELEMENT[b]\nBOOLEAN[c]\nONOFF[d]\nSTRING[e]", listener -> {
            InOrder order = inOrder(listener);
            order.verify(listener).elementDefined(1, "a");
            order.verify(listener).emptyElementDefined(2, "b");
            order.verify(listener).booleanDefined(3, "c");
            order.verify(listener).onOffDefined(4, "d");
            order.verify(listener).stringDefined(5, "e");
        });
    }

    @Test
    public void attribute_definitions_are_signaled() {
        verifyParsing("ATTRIBUTE[a:b]", listener -> {
            InOrder order = inOrder(listener);
            order.verify(listener).attributeDefined(1, "a", "b");
        });
    }

    @Test
    public void invalid_lines_are_signaled() {
        verifyParsing("lol, I dunno!\nIt Broke...", listener -> {
            InOrder order = inOrder(listener);
            order.verify(listener).invalidLine(1, "lol, I dunno!");
            order.verify(listener).invalidLine(2, "It Broke...");
        });
    }
}
