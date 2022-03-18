TagCreator.class // Static utility class for creating all tags
import static j2html.TagCreator.*; // Use static star import


Config.class // Holds all configuration.  Offers global configuration or customizable instances
Config.closeEmptyTags = true // Global options are public, static and mutable.
Config.global() // Copy all static Config fields into an instance.  Instances are immutable
Config.defaults() // A Config with defaults that are independent of global options
Config.global().withEmptyTagsClosed(true) // A Config that is different from the global options
Config.defaults().withEmptyTagsClosed(true) // A Config that is different from the default options


TagCreator.join() // Method for joining small snippets, like:
p(join("This paragraph has", b("bold"), "and", i("italic"), "text."))


TagCreator.iff(boolean condition, T ifValue) // If-expression for use in method calls
div().withClasses("menu-element", iff(isActive, "active"))


TagCreator.iffElse(boolean condition, T ifValue, T elseValue) // If/else-expression for use in method calls
div().withClasses("menu-element", iffElse(isActive, "active", "not-active"))


Tag.class // Is extended by ContainerTag (ex <div></div> and EmptyTag (ex <br>)
Tag.attr(String attribute, Object value) // Set an attribute on the tag
Tag.withXyz(String value) // Calls attr with predefined attribute (ex .withId, .withClass, etc.)
Tag.render(HtmlBuilder builder) // Render HTML using the given builder.
Tag.render() // Shortcut for rendering flat HTML into a string using global Config.
ContainerTag.renderFormatted() // Shortcut for rendering indented HTML into a string using global Config.

HtmlBuilder.class // Interface for composing HTML. Implemented by FlatHtml and IndentedHtml
FlatHtml.into(Appendable) // Render into a stream, file, etc. without indentation or line breaks
FlatHtml.into(Appendable appendable, Config config) // Customize rendering of flat html
IndentedHtml.into(Appendable) // Render human-readable HTML into an stream, file, etc.
IndentedHtml.into(Appendable appendable, Config config) // Customize rendering of intended html
ul(li("one"), li("two")).render(IndentedHtml.inMemory()).toString() // Similar to renderFormatted()
ul(li("one"), li("two")).render(IndentedHtml.into(filewriter)) // Write HTML into a file
