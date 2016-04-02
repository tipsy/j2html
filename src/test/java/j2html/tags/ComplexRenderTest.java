package j2html.tags;

import j2html.attributes.*;
import org.junit.*;
import static j2html.TagCreator.*;
import static org.junit.Assert.*;

public class ComplexRenderTest {

    private String renderTest() {
        boolean USER_SHOULD_LOG_IN = true;
        boolean USER_SHOULD_SIGN_UP = false;
        return document().render() +
                html().with(
                        head().with(
                                title().withText("Test")
                        ),
                        body().with(
                                header().with(
                                        h1().with(
                                                text("Test Header "),
                                                a("with link").withHref("http://example.com"),
                                                text(".")
                                        )
                                ),
                                main().with(
                                        h2("Test Form"),
                                        div().with(
                                                input().withType("email").withName("email").withPlaceholder("Email"),
                                                input().withType("password").withName("password").withPlaceholder("Password")
                                        ).condWith(USER_SHOULD_LOG_IN, button().withType("submit").withText("Login")
                                        ).condWith(USER_SHOULD_SIGN_UP, button().withType("submit").withText("Signup")
                                        )
                                ),
                                footer().attr(Attr.CLASS, "footer").condAttr(1 == 1, Attr.ID, "id").withText("Test Footer"),
                                script().withSrc("/testScript.js")
                        )
                ).render();
    }

    @Test
    public void testComplexRender() {
        String expectedResult = "<!DOCTYPE html><html><head><title>Test</title></head><body><header><h1>Test Header <a href=\"http://example.com\">with link</a>.</h1></header><main><h2>Test Form</h2><div><input type=\"email\" name=\"email\" placeholder=\"Email\"><input type=\"password\" name=\"password\" placeholder=\"Password\"><button type=\"submit\">Login</button></div></main><footer class=\"footer\" id=\"id\">Test Footer</footer><script src=\"/testScript.js\"></script></body></html>";
        assertEquals(renderTest(), expectedResult);
    }
}
