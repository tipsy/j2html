package j2html.test.testPerformance;

import org.junit.Test;

import static j2html.src.tags.TagCreator.*;

public class RenderSpeed {

    private String renderTest(int i) {
        return html().with(
                head().with(
                        title().withText("Test " + i)
                ),
                body().with(
                        header().with(
                                h1().withText("Test Header")
                        ),
                        main().with(
                                h2().withText("Test Form"),
                                div().with(
                                        input().withType("email").withName("email").withPlaceholder("Email"),
                                        input().withType("password").withName("password").withPlaceholder("password"),
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
        long startTime = System.nanoTime();
        int pages = 100000;
        for(int i = 0; i < pages; i++){
            String testString = renderTest(i);
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime)/1000000;
        System.out.print("Rendered 100000 pages in " + duration + "ms");
    }

}
