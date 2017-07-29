package j2html.printer;

import org.junit.Test;

import static j2html.TagCreator.body;
import static j2html.TagCreator.h1;
import static j2html.TagCreator.img;

/**
 * @author chmuchme
 * @since 0.1
 * on 01/05/17.
 */
public class DefaultPrinterTest {

    @Test
    public void test1(){
        System.out.println(body().with(
                h1("Heading!").withClass("example"),
                img().withSrc("img/hello.png")
        ).render());
    }

    @Test
    public void test2(){
        ConfigurablePrinter configurablePrinter = new ConfigurablePrinter(false, "    ");
        System.out.println(body().with(
                h1("Heading!").withClass("example"),
                img().withSrc("img/hello.png")
        ).render(configurablePrinter));
    }

    @Test
    public void test3(){
        ConfigurablePrinter configurablePrinter = new ConfigurablePrinter(true, "    ");
        System.out.println(body().with(
                h1("Heading!").withClass("example"),
                img().withSrc("img/hello.png")
        ).render(configurablePrinter));
    }

    @Test
    public void test4(){
        ConfigurablePrinter configurablePrinter = new ConfigurablePrinter(true, "");
        System.out.println(body().with(
                h1("Heading!").withClass("example"),
                img().withSrc("img/hello.png")
        ).render(configurablePrinter));
    }
}
