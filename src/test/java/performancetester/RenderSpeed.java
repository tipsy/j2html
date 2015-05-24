package performancetester;

import j2html.tags.ContainerTag;
import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import static j2html.TagCreator.*;
import static org.junit.Assert.assertEquals;

public class RenderSpeed {

    /**
     * This test class is only used during development of j2html, to see if changes to data-types and rendering
     * techniques have a positive or negative impact
     */

    public int iterations = 100000;
    public int precision = 10;

    @Test
    public void testFullPageRenderSpeed() {
        ContainerTag complexTestTag = html().with(body().with(header(), main().with(p("Main stuff...")), footer().condWith(1 == 1, p("Conditional with!"))));
        String expectedResult = "<html><body><header></header><main><p>Main stuff...</p></main><footer><p>Conditional with!</p></footer></body></html>";
        assertEquals(complexTestTag.render(), (expectedResult));
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        measureAvgMethodTime(this::fullPageRenderTest, iterations, precision, methodName);
    }

    @Test
    public void testAttributeRenderSpeed() {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        measureAvgMethodTime(this::attributeRenderTest, iterations, precision, methodName);
    }

    public void attributeRenderTest(int i) {
        tag("div " + i)
                .attr("just", "adding")
                .attr("some", "pretty")
                .attr("random", "attributes")
                .attr("to", "this")
                .attr("div", "here").render();
    }

    public String fullPageRenderTest(int i) {
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
                                        input().withType("password").withName("repeatPassword").withPlaceholder("Repeat password"),
                                        input().withType("password").withName("repeatPasswordAgain").withPlaceholder("Repeat password again"),
                                        button().withType("submit").withText("Login")
                                ),
                                div().withText(
                                        "<script>alert(1)</script>"
                                )
                        ),
                        footer().withText("Test Footer"),
                        script().withSrc("/testScript.js")
                )
        ).render();
    }

    /**
     * Please tell me if there is a proper way to do this :D
     *
     * @param method     the method to measure
     * @param iterations the number of times to run the method
     * @param precision  the number of times to run the timer
     * @param methodName the name of the method (only used for output)
     */
    private void measureAvgMethodTime(IntConsumer method, int iterations, int precision, String methodName) {
        double averageTime = LongStream.range(0, precision).map(l -> measureMethodTime(method, iterations)).average().getAsDouble();
        System.out.println("\n" + methodName + ": " + averageTime + "ms was avg runtime per " + String.format("%,d", iterations) + " iterations (ran " + precision + " times) \n");
    }

    private long measureMethodTime(IntConsumer method, int iterations) {
        long startTime = System.nanoTime();
        IntStream.range(0, iterations).parallel().forEach(method);
        long endTime = System.nanoTime();
        System.out.print(TimeUnit.NANOSECONDS.toMillis(endTime - startTime) + " ");
        return TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
    }

}
