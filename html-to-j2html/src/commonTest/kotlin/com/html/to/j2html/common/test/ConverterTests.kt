package com.html.to.j2html.common.test

import kotlin.test.Test
import kotlin.test.assertEquals
import com.html.to.j2html.common.convert

class ConverterTests {

    @Test
    fun createDiv() {
        val input = """
            <div>Hello world</div>
        """.trimIndent()


        val expectedOutput = """
            div("Hello world")
        """.trimIndent()

        val output = convert(input)

        assertEquals(expectedOutput, output)
    }

    @Test
    fun attributes() {
        val input = """
            <div att="one two three">Hello world</div>
        """.trimIndent()


        val expectedOutput = """
            div().attr("att", "one two three")
              .with(text("Hello world"))
        """.trimIndent()

        val output = convert(input)

        assertEquals(expectedOutput, output)
    }

    @Test
    fun classes() {
        val input = """
            <div class="one two three">Hello world</div>
        """.trimIndent()

        val expectedOutput = """
            div().withClasses("one", "two", "three")
              .with(text("Hello world"))
        """.trimIndent()

        val output = convert(input)

        assertEquals(expectedOutput, output)
    }

    @Test
    fun inputSnippetWithMainLevelStringsAndSpacesThatNeedToBePreservedTest(){
        val inputSnippetWithMainLevelStringsAndSpacesThatNeedToBePreserved = """
            <!-- text link text -->
            text1 <a>link</a> text2
            <!-- post comment -->
        """.trimIndent()

        val expectedOutput = """
            // text link text
            text("text1 "),
            a("link"),
            text(" text2")
            // post comment
            
        """.trimIndent()
        val output = convert(inputSnippetWithMainLevelStringsAndSpacesThatNeedToBePreserved)
        assertEquals(expectedOutput, output)

    }

    @Test
    fun inputSnippetWithNewLinesThatAreConsideredSignificantByBrowsersTest(){

        val inputSnippetWithNewLinesThatAreConsideredSignificantByBrowsers = """
            <!-- text link text -->
            text1
            <a>link</a>
            text2
            <!-- post comment -->
        """.trimIndent()

        // this test documents a compromise with the best solution we could find, for the problem of
        // how to sanitize new lines and whitespace without knowing for sure what is and
        // isn't significant in the user submitted input.
        // the input html renders in a browser as "text1 link text2"
        // and at the time of writing this, the html produced by
        //     text("text1"),
        //     a("link"),
        //     text("text2")
        // renders as "text1 link text2" or as "text1linktext2" depending on
        // wether renderFormatted() or render() is used.
        // But as a compomise, that still seems like the most accurate way of generating the code

        val expectedOutput = """
            // text link text
            text("text1"),
            a("link"),
            text("text2")
            // post comment
            
        """.trimIndent()
        val output = convert(inputSnippetWithNewLinesThatAreConsideredSignificantByBrowsers)
        assertEquals(expectedOutput, output)

    }

    @Test
    fun textWithSpacesAroundALinkTest(){

        val textWithSpacesAroundALinkAndAnAnchorHRefLink = """
           <p>before link $ space <a href="#faq">link</a> after link & a space</p>
        """

        val expectedOutput = """
            p(
                text("before link $ space "), 
                a().withHref("#faq")
                  .with(text("link")), 
                text(" after link & a space")
            )
        """.trimIndent()

        val output = convert(textWithSpacesAroundALinkAndAnAnchorHRefLink)
        assertEquals(expectedOutput, output)

    }

    @Test
    fun inputSnippetWithNewLinesAtBegginingAndEndOfParagraphTest() {

        // not that easy to fulfill without breaking textWithSpacesAroundALinkTest()

        val inputSnippetWithNewLinesAtBegginingAndEndOfParagraph =
            """
            <main>
                <div>
                    <p>
                        Hello world
                    </p>
                </div>
            </main>
            """.trimIndent()

        val expectedOutput = """
            main(
                div(
                    p("Hello world")
                )
            )
        """.trimIndent()

        val output = convert(inputSnippetWithNewLinesAtBegginingAndEndOfParagraph)

        assertEquals(expectedOutput, output)
    }

    @Test
    fun inputSnippetOfj2htmlDefaultExampleTest() {

        val inputSnippetOfj2htmlExamplePage = """
            <!-- j2html default snippet -->
            <div id="employees">
                <div class="employee">
                    <h2>David</h2>
                    <img src="/img/david.png">
                    <p>Creator of Bad Libraries</p>
                </div>
                <div class="employee">
                    <h2>Christian</h2>
                    <img src="/img/christian.png">
                    <p>Fanboi of Jenkins</p>
                </div>
                <div class="employee">
                    <h2>Paul\n</h2>
                    <img src="/img/paul.png">
                    <p>Hater of Lambda Expressions</p>
                </div>
            </div>            
        """.trimIndent()

        val expectedOutput = """
            // j2html default snippet
            div().withId("employees")
              .with(
                div().withClasses("employee")
                  .with(
                    h2("David"), 
                    img().withSrc("/img/david.png"), 
                    p("Creator of Bad Libraries")
                ), 
                div().withClasses("employee")
                  .with(
                    h2("Christian"), 
                    img().withSrc("/img/christian.png"), 
                    p("Fanboi of Jenkins")
                ), 
                div().withClasses("employee")
                  .with(
                    h2("Paul\\n"), 
                    img().withSrc("/img/paul.png"), 
                    p("Hater of Lambda Expressions")
                )
            )
        """.trimIndent()

        val output = convert(inputSnippetOfj2htmlExamplePage)

        assertEquals(expectedOutput, output)

    }
    @Test
    fun inputSnippetWithDataAttributesBooleanParsingAndIntegerParsingTest() {

        val inputSnippetWithDataAttributesBooleanParsingAndIntegerParsing = """
            <!-- special attributes test snippet -->
            <div class="special-attr-test" tabindex="5">
                <h2>Special attribute animals</h2>
                <ul>
                  <li data-animal-type="bird" translate>Owl</li>
                  <li data-animal-type="fish" translate="true">Salmon</li>
                  <li data-animal-type="spider" translate="yes">Tarantula</li>
                  <li data-animal-type="bear" translate="translate">Panda</li>
                  <li data-animal-type="bird" translate="no">Penguin</li>
                  <li data-animal-type="fish" translate="false">Megalodon</li>
                </ul>
            </div>
        """.trimIndent()

        val expectedOutput = """
            // special attributes test snippet
            div()
              .withClasses("special-attr-test")
              .withTabindex(5)
              .with(
                h2("Special attribute animals"), 
                ul(
                    li()
                      .withData("animal-type","bird")
                      .withCondTranslate(true)
                      .with(text("Owl")), 
                    li()
                      .withData("animal-type","fish")
                      .withCondTranslate(true)
                      .with(text("Salmon")), 
                    li()
                      .withData("animal-type","spider")
                      .withCondTranslate(true)
                      .with(text("Tarantula")), 
                    li()
                      .withData("animal-type","bear")
                      .withCondTranslate(true)
                      .with(text("Panda")), 
                    li()
                      .withData("animal-type","bird")
                      .withCondTranslate(false)
                      .with(text("Penguin")), 
                    li()
                      .withData("animal-type","fish")
                      .withCondTranslate(false)
                      .with(text("Megalodon"))
                )
            )
        """.trimIndent()

        val output = convert(inputSnippetWithDataAttributesBooleanParsingAndIntegerParsing)

        assertEquals(expectedOutput, output)
    }

    @Test
    fun inputSnippetOfCommonHtmxUseCasesTest() {

        val inputSnippetOfCommonHtmxUseCases = """
            <!-- htmx snippets -->
            <div id="misc-htmx-snippets">
                <h2>HTMX snippets</h2>
                <!-- https://htmx.org/examples/lazy-load/ -->
                <div id="htmx lazy-loading">
                    <h3>Lazy loading</h3>
                    <div hx-get="/graph" hx-trigger="load">
                      <img  alt="Result loading..." class="htmx-indicator" width="150" src="/img/bars.svg"/>
                    </div>
                </div>
            </div>
        """.trimIndent()

        val expectedOutput = """
            // htmx snippets
            div().withId("misc-htmx-snippets")
              .with(
                h2("HTMX snippets"), 
                // https://htmx.org/examples/lazy-load/
                div().withId("htmx lazy-loading")
                  .with(
                    h3("Lazy loading"), 
                    div()
                      .attr("hx-get", "/graph")
                      .attr("hx-trigger", "load")
                      .with(
                        img()
                          .withAlt("Result loading...")
                          .withClasses("htmx-indicator")
                          .withWidth("150")
                          .withSrc("/img/bars.svg")
                    )
                )
            )
        """.trimIndent()

        val output = convert(inputSnippetOfCommonHtmxUseCases)

        assertEquals(expectedOutput, output)
    }

    @Test
    fun inputSnippetOfHtmxInfiniteScrollTest() {

        val inputSnippetOfHtmxInfiniteScroll = """
            <!-- htmx snippets -->
            <div id="misc-htmx-snippets">
                <h2>HTMX snippets</h2>
                <!-- https://htmx.org/examples/infinite-scroll/ -->
                <div>
                    <h3>Infinite scroll</h3>
                    <tr hx-get="/contacts/?page=2"
                        hx-trigger="revealed"
                        hx-swap="afterend">
                      <td>Agent Smith</td>
                      <td>void29@null.org</td>
                      <td>55F49448C0</td>
                    </tr>
                </div>
            </div>
        """.trimIndent()

        val expectedOutput = """
            // htmx snippets
            div().withId("misc-htmx-snippets")
              .with(
                h2("HTMX snippets"), 
                // https://htmx.org/examples/infinite-scroll/
                div(
                    h3("Infinite scroll"), 
                    tr()
                      .attr("hx-get", "/contacts/?page=2")
                      .attr("hx-trigger", "revealed")
                      .attr("hx-swap", "afterend")
                      .with(
                        td("Agent Smith"), 
                        td("void29@null.org"), 
                        td("55F49448C0")
                    )
                )
            )
        """.trimIndent()

        val output = convert(inputSnippetOfHtmxInfiniteScroll)

        assertEquals(expectedOutput, output)
    }

    @Test
    fun inputSnippetOfSomePicoCssComponentsAndCharactersThatNeedEscapingTest() {

        val inputSnippetOfAPicoCssAccordionWithCharactersThatNeedEscapingBothInSingleAndMultilineStrings = """
            <!-- pico css snippets-->
            <div id="misc-picocss-snippets">
              <h2>Pico CSS snippets</h2>
              <!-- https://github.com/picocss/examples/blob/master/v2-html-classless/index.html -->
              <div id="pico-css-accordion">
                <!-- Accordions -->
                <section id="accordions">
                  <h3>Accordions</h3>
                  <details>
                    <summary>Accordion 1</summary>
                    <p>
                      n \n \\n \\\n \\\\n f \f \\f \\\f \\\\f Lorem ipsum dolor sit amet\", consectetur ad"ipiscing elit. Pelle\"ntesque urna diam,
                      " \" \\" \\\" \\\\" tincidunt nec port"a sed, auctor id veli\t. Et"iam ve\\nenatis nisl ut "orci consequat, vitae
                      tempus quam commodo. Nulla non mauris ipsum. Aliquam eu posuere orci. Nulla co\\\\nvallis
                      lectus rutrum quam hendrerit, in facilisis elit sollicitu"din. Ma"uri"s pulvinar pulvinar
                      mi, dictum tr"istique elit auctor quis. Maecenas ac ipsum u\\"ltrices, porta turpis sit
                      amet, congue turpis.
                    </p>
                    <p>n \n \\n \\\n \\\\n f \f \\f \\\f \\\\f Lorem ipsum dolor sit amet\", consectetur ad"ipiscing elit. Pelle\"ntesque urna diam, " \" \\" \\\" \\\\" tincidunt nec port"a sed, auctor id veli\t. Et"iam ve\\nenatis nisl ut "orci consequat, vitae tempus quam commodo. Nulla non mauris ipsum. Aliquam eu posuere orci. Nulla co\\\\nvallis lectus rutrum quam hendrerit, in facilisis elit sollicitu"din. Ma"uri"s pulvinar pulvinar mi, dictum tr"istique elit auctor quis. Maecenas ac ipsum u\\"ltrices, porta turpis sit amet, congue turpis.</p>
                  </details>
                  <details open>
                    <summary>Accordion 2</summary>
                    <ul>
                      <li>Vestibu\"lum i"d e"lit q"uis massa interdum sodales.</li>
                      <li>n \n \\n \\\n \\\\n Nunc quis eros vel odio pretium tincidunt nec quis neque.</li>
                      <li>f \f \\f \\\f \\\\f Quisque sed eros non eros ornare elementum.</li>
                      <li>" \" \\" \\\" \\\\" Cras sed libero aliquet, porta dolor quis, dapibus ipsum.</li>
                      <li>' \' \\' \\\' \\\\' Cras sed libero aliquet, porta dolor quis, dapibus ipsum.</li>
                    </ul>
                  </details>
                </section>
              </div>
            </div>
            """.trimIndent()

        val expectedOutput = """
            // pico css snippets
            div().withId("misc-picocss-snippets")
              .with(
                h2("Pico CSS snippets"), 
                // https://github.com/picocss/examples/blob/master/v2-html-classless/index.html
                div().withId("pico-css-accordion")
                  .with(
                    // Accordions
                    section().withId("accordions")
                      .with(
                        h3("Accordions"), 
                        details(
                            summary("Accordion 1"), 
                            p(
                              ""${'"'}
                              n \\n \\\\n \\\\\\n \\\\\\\\n f \\f \\\\f \\\\\\f \\\\\\\\f Lorem ipsum dolor sit amet\\\", consectetur ad\"ipiscing elit. Pelle\\\"ntesque urna diam, \" \\\" \\\\\" \\\\\\\" \\\\\\\\\" tincidunt nec port\"a sed, auctor id veli\\t. Et\"iam ve\\\\nenatis nisl ut \"orci consequat, vitae tempus quam commodo. Nulla non mauris ipsum. Aliquam eu posuere orci. Nulla co\\\\\\\\nvallis lectus rutrum quam hendrerit, in facilisis elit sollicitu\"din. Ma\"uri\"s pulvinar pulvinar mi, dictum tr\"istique elit auctor quis. Maecenas ac ipsum u\\\\\"ltrices, porta turpis sit amet, congue turpis.
                              ""${'"'}), 
                            p(
                              ""${'"'}
                              n \\n \\\\n \\\\\\n \\\\\\\\n f \\f \\\\f \\\\\\f \\\\\\\\f Lorem ipsum dolor sit amet\\\", consectetur ad\"ipiscing elit. Pelle\\\"ntesque urna diam, \" \\\" \\\\\" \\\\\\\" \\\\\\\\\" tincidunt nec port\"a sed, auctor id veli\\t. Et\"iam ve\\\\nenatis nisl ut \"orci consequat, vitae tempus quam commodo. Nulla non mauris ipsum. Aliquam eu posuere orci. Nulla co\\\\\\\\nvallis lectus rutrum quam hendrerit, in facilisis elit sollicitu\"din. Ma\"uri\"s pulvinar pulvinar mi, dictum tr\"istique elit auctor quis. Maecenas ac ipsum u\\\\\"ltrices, porta turpis sit amet, congue turpis.
                              ""${'"'})
                        ), 
                        details().withCondOpen(true)
                          .with(
                            summary("Accordion 2"), 
                            ul(
                                li("Vestibu\\\"lum i\"d e\"lit q\"uis massa interdum sodales."), 
                                li("n \\n \\\\n \\\\\\n \\\\\\\\n Nunc quis eros vel odio pretium tincidunt nec quis neque."), 
                                li("f \\f \\\\f \\\\\\f \\\\\\\\f Quisque sed eros non eros ornare elementum."), 
                                li("\" \\\" \\\\\" \\\\\\\" \\\\\\\\\" Cras sed libero aliquet, porta dolor quis, dapibus ipsum."), 
                                li("' \\' \\\\' \\\\\\' \\\\\\\\' Cras sed libero aliquet, porta dolor quis, dapibus ipsum.")
                            )
                        )
                    )
                )
            )
        """.trimIndent()

        val output =
            convert(inputSnippetOfAPicoCssAccordionWithCharactersThatNeedEscapingBothInSingleAndMultilineStrings)

        assertEquals(expectedOutput, output)
    }

    @Test
    fun inputSnippetContainingUnsupportedTagsTest() {

        val inputSnippetContainingUnsupportedTags = """
            <!-- https://flowbite.com/docs/components/drawer/ -->
            <div id="flowbyte drawer">
                <!-- drawer component -->
                <div id="drawer-example" class="fixed top-0 left-0 z-40 h-screen p-4 overflow-y-auto transition-transform -translate-x-full bg-white w-80 dark:bg-gray-800" tabindex="-1" aria-labelledby="drawer-label">
                   <h5 id="drawer-label" class="inline-flex items-center mb-4 text-base font-semibold text-gray-500 dark:text-gray-400"><svg class="w-4 h-4 me-2.5" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="currentColor" viewBox="0 0 20 20">
                    <path d="M10 .5a9.5 9.5 0 1 0 9.5 9.5A9.51 9.51 0 0 0 10 .5ZM9.5 4a1.5 1.5 0 1 1 0 3 1.5 1.5 0 0 1 0-3ZM12 15H8a1 1 0 0 1 0-2h1v-3H8a1 1 0 0 1 0-2h2a1 1 0 0 1 1 1v4h1a1 1 0 0 1 0 2Z"/>
                  </svg>Info</h5>
                </div>
            </div>
        """.trimIndent()

        val expectedOutput = """
            // https://flowbite.com/docs/components/drawer/
            div().withId("flowbyte drawer")
              .with(
                // drawer component
                div()
                  .withId("drawer-example")
                  .withClasses("fixed", "top-0", "left-0", "z-40", "h-screen", "p-4", "overflow-y-auto", "transition-transform", "-translate-x-full", "bg-white", "w-80", "dark:bg-gray-800")
                  .withTabindex(-1)
                  .attr("aria-labelledby", "drawer-label")
                  .with(
                    h5()
                      .withId("drawer-label")
                      .withClasses("inline-flex", "items-center", "mb-4", "text-base", "font-semibold", "text-gray-500", "dark:text-gray-400")
                      .with(
                        rawHtml(
                        ""${'"'}
                          <svg class="w-4 h-4 me-2.5" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="currentColor" viewbox="0 0 20 20">
                            <path d="M10 .5a9.5 9.5 0 1 0 9.5 9.5A9.51 9.51 0 0 0 10 .5ZM9.5 4a1.5 1.5 0 1 1 0 3 1.5 1.5 0 0 1 0-3ZM12 15H8a1 1 0 0 1 0-2h1v-3H8a1 1 0 0 1 0-2h2a1 1 0 0 1 1 1v4h1a1 1 0 0 1 0 2Z"/>
                          </svg>
                        ""${'"'}), 
                        text("Info")
                    )
                )
            )
        """.trimIndent()

        val output = convert(inputSnippetContainingUnsupportedTags)

        assertEquals(expectedOutput, output)
    }





    @Test
    fun inputSnippetWithCharactersThatNeedEscapingWhileInAUnsupportedTagTest() {

        val inputSnippetWithCharactersThatNeedEscapingWhileInAUnsupportedTag = """
            <!-- https://flowbite.com/docs/components/toast/ -->
            <div id="flowbyte simple toast">
                <h3>Flowbyte simple toast</h3>
                <div id="toast-simple" class="flex items-center w-full max-w-xs p-4 space-x-4 rtl:space-x-reverse text-gray-500 bg-white divide-x rtl:divide-x-reverse divide-gray-200 rounded-lg shadow dark:text-gray-400 dark:divide-gray-700 space-x dark:bg-gray-800" role="alert">
                    <svg class="w-5 h-5 text-blue-600 dark:text-blue-500 rotate-45" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 18 20">
                         <li>n \n \\n \\\n \\\\n  Nunc quis eros vel odio pretium tincidunt nec quis neque.</li>
                         <li>f \f \\f \\\f \\\\f Quisque sed eros non eros ornare elementum.</li>
                         <li>" \" \\" \\\" \\\\" Cras sed libero aliquet, porta dolor quis, dapibus ipsum.</li>
                         <li>' \' \\' \\\' \\\\' Cras sed libero aliquet, porta dolor quis, dapibus ipsum.</li>
                        <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="m9 17 8 2L9 1 1 19l8-2Zm0 0V9"/>
                    </svg>
                    <div class="ps-4 text-sm font-normal">Message sent successfully.</div>
                </div>
            </div>
        """.trimIndent()

        val expectedOutput = """
            // https://flowbite.com/docs/components/toast/
            div().withId("flowbyte simple toast")
              .with(
                h3("Flowbyte simple toast"), 
                div()
                  .withId("toast-simple")
                  .withClasses("flex", "items-center", "w-full", "max-w-xs", "p-4", "space-x-4", "rtl:space-x-reverse", "text-gray-500", "bg-white", "divide-x", "rtl:divide-x-reverse", "divide-gray-200", "rounded-lg", "shadow", "dark:text-gray-400", "dark:divide-gray-700", "space-x", "dark:bg-gray-800")
                  .attr("role", "alert")
                  .with(
                    rawHtml(
                    ""${'"'}
                      <svg class="w-5 h-5 text-blue-600 dark:text-blue-500 rotate-45" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewbox="0 0 18 20">
                        <li>n \\n \\\\n \\\\\\n \\\\\\\\n  Nunc quis eros vel odio pretium tincidunt nec quis neque.</li>
                        <li>f \\f \\\\f \\\\\\f \\\\\\\\f Quisque sed eros non eros ornare elementum.</li>
                        <li>\" \\\" \\\\\" \\\\\\\" \\\\\\\\\" Cras sed libero aliquet, porta dolor quis, dapibus ipsum.</li>
                        <li>' \\' \\\\' \\\\\\' \\\\\\\\' Cras sed libero aliquet, porta dolor quis, dapibus ipsum.</li>
                        <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="m9 17 8 2L9 1 1 19l8-2Zm0 0V9"/>
                      </svg>
                    ""${'"'}), 
                    div().withClasses("ps-4", "text-sm", "font-normal")
                      .with(text("Message sent successfully."))
                )
            )
        """.trimIndent()

        val output = convert(inputSnippetWithCharactersThatNeedEscapingWhileInAUnsupportedTag)

        assertEquals(expectedOutput, output)
    }

    @Test
    fun inputSnippetOfSomeTailWindComponentsTest() {

        val inputSnippetOfSomeTailWindComponents = """
                <!-- https://tw-elements.com/docs/standard/forms/select/ -->
                <div id="tailwind-select-multiple">
                    <h3>TW select multiple</h3>
                    <select data-te-select-init multiple>
                      <option value="1">One</option>
                      <option value="2">Two</option>
                      <option value="3">Three</option>
                    </select>
                    <label data-te-select-label-ref>Example label</label>
                </div>
        """.trimIndent()

        val expectedOutput = """
            // https://tw-elements.com/docs/standard/forms/select/
            div().withId("tailwind-select-multiple")
              .with(
                h3("TW select multiple"), 
                select()
                  .withData("te-select-init","")
                  .withCondMultiple(true)
                  .with(
                    option().withValue("1")
                      .with(text("One")), 
                    option().withValue("2")
                      .with(text("Two")), 
                    option().withValue("3")
                      .with(text("Three"))
                ), 
                label().withData("te-select-label-ref","")
                  .with(text("Example label"))
            )
        """.trimIndent()

        val output = convert(inputSnippetOfSomeTailWindComponents)

        assertEquals(expectedOutput, output)
    }

    @Test
    fun inputSnippetWithSeveralTopLevelElementsPlusCommentsBeforeAndAfterTest() {

        val inputSnippetWithSeveralTopLevelElementsPlusCommentsBeforeAndAfter = """
                <!-- test beggining comment -->
                <div id="first main level tag">
                </div>
                <div id="second main level tag">
                    <div>
                        <p>Hello world</p>
                    </div>
                </div>
                <!-- test end comment -->
            """.trimIndent()

        val expectedOutput = """
            // test beggining comment
            div().withId("first main level tag"),
            div().withId("second main level tag")
              .with(
                div(
                    p("Hello world")
                )
            )
            // test end comment
            
        """.trimIndent()

        val output = convert(inputSnippetWithSeveralTopLevelElementsPlusCommentsBeforeAndAfter)

        assertEquals(expectedOutput, output)
    }

    @Test
    fun inputSnippetWithMultilineCommentsTest() {

        val inputSnippetWithMultilineComments = """
                <!-- test beggining 
                 multiline comment -->
                <div id="first main level tag">
                </div>
                <div id="second main level tag">
                    <div>
                        <p>Hello world</p>
                    </div>
                </div>
                <!-- test end 
                multiline comment -->
            """.trimIndent()

        val expectedOutput = """
            // test beggining 
            // multiline comment
            div().withId("first main level tag"),
            div().withId("second main level tag")
              .with(
                div(
                    p("Hello world")
                )
            )
            // test end 
            // multiline comment
            
        """.trimIndent()

        val output = convert(inputSnippetWithMultilineComments)

        assertEquals(expectedOutput, output)
    }

}
