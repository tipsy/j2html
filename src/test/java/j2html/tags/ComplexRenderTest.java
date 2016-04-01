package j2html.tags;

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
                                        h1("Test Header")
                                ),
                                main().with(
                                        h2("Test Form"),
                                        div().with(
                                                input().withType("email").withName("email").withPlaceholder("Email"),
                                                input().withType("password").withName("password").withPlaceholder("Password")
                                        ).condWith(USER_SHOULD_LOG_IN,
                                                button().withType("submit").withText("Login")
                                        ).condWith(USER_SHOULD_SIGN_UP,
                                                button().withType("submit").withText("Signup")
                                        )
                                ),
                                footer().withText("Test Footer"),
                                script().withSrc("/testScript.js")
                        )
                ).render();
    }

    @Test
    public void testComplexRender() {
        String expectedResult = "<!DOCTYPE html><html><head><title>Test</title></head><body><header><h1>Test Header</h1></header><main><h2>Test Form</h2><div><input type=\"email\" name=\"email\" placeholder=\"Email\"><input type=\"password\" name=\"password\" placeholder=\"Password\"><button type=\"submit\">Login</button></div></main><footer>Test Footer</footer><script src=\"/testScript.js\"></script></body></html>";
        assertEquals(renderTest(), expectedResult);
    }
}
