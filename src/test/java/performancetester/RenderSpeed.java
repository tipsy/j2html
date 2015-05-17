package performancetester;

import org.junit.Test;

import java.util.stream.IntStream;

import static j2html.TagCreator.*;

public class RenderSpeed {

    private String renderTest(int i) {
        return html().with(
                head().with(
                        title().withText("Test " + i)
                ),
                body().with(
                        header().with(
                                h1("Test Header")
                        ),
                        main().with(
                                h2("Test Form"),
                                div().with(
                                        input().withType("email").withName("email").withPlaceholder("Email"),
                                        input().withType("password").withName("password").withPlaceholder("Password"),
                                        button().withType("submit").withText("Login")
                                )
                        ),
                        footer().withText("Test Footer"),
                        script().withSrc("/testScript.js")
                )
        ).render();
    }

    @Test
    public void testRenderSpeed() {
        int numberOfRenders = 100000;
        long startTime = System.nanoTime();
        IntStream.range(0, numberOfRenders).parallel().forEach(
            this::renderTest
        );
        long endTime = System.nanoTime();
        long timeInMs = (endTime - startTime) / 1000000;
        System.out.print("Rendered " + numberOfRenders + " pages in " + timeInMs + "ms");
    }

}
