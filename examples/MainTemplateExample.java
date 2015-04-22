public class MainTemplate {

    public static String render(String title, Tag... contentTags) {
        return document().render() +
                html().with(
                        head().with(
                                title().withText(title),
                                link().withRel("stylesheet").withHref("/css/main.css")
                        ),
                        body().with(
                                header().with(
                                        h1().withText(title)
                                ),
                                main().with(
                                        contentTags //content from other template
                                ),
                                footer().withText("Footing text"),
                                script().withSrc("https://code.jquery.com/jquery-2.1.3.min.js")
                        )
                ).render();
    }

}


MainTemplate.render(
    "Form test", //this is the "String title" parameter
    //Everything below here goes into the "Tag... contentTags" parameter
    // "..." means "varargs", which means you can add as many Tags as you like
    h2().withText("Please log in"),
    form().withMethod("post").with(
            emailInput("Email"), //see below for these three fields
            passwordInput("Password"),
            submitButton("Log in")
)


// you can create methods like these to return fully configured 
// HTML input fields/buttons (or any other tag)
public static Tag emailInput(String placeholder) {
    return input()
            .withType("email")
            .withId("email")
            .withName("email")
            .withPlaceholder(placeholder)
            .isRequired();
}

public static Tag passwordInput(String placeholder) {
    return input()
            .withType("password")
            .withId("password")
            .withName("password")
            .withPlaceholder(placeholder)
            .isRequired();
}

public static Tag submitButton(String text) {
    return button()
            .withType("submit")
            .withText(text);
}

//  OUTPUT
//  If you call MainTemplate.render with the above setup, it will return this: 
//  <!DOCTYPE html><html><head><title>Form test</title><link rel="stylesheet" href="/css/main.css"></link></head><body><header><h1>Form test</h1></header><main><h2>Please log in</h2><form method="post"><input type="email" id="email" name="email" placeholder="Email" required></input><input type="password" id="password" name="password" placeholder="Password" required></input><button type="submit">Log in</button></form></main><footer>Footing text</footer><script src="https://code.jquery.com/jquery-2.1.3.min.js"></script></body></html>

//  Which unminified will look like this:
//  <!DOCTYPE html>
//  <html>
//  <head>
//      <title>Form test</title>
//     <link rel="stylesheet" href="/css/main.css"></link>
//  </head>
//  
//  <body>
//      <header>
//          <h1>Form test</h1>
//      </header>
//      <main>
//          <h2>Please log in</h2>
//          <form method="post">
//              <input type="email" id="email" name="email" placeholder="Email" required></input>
//              <input type="password" id="password" name="password" placeholder="Password" required></input>
//              <button type="submit">Log in</button>
//          </form>
//      </main>
//      <footer>Footing text</footer>
//      <script src="https://code.jquery.com/jquery-2.1.3.min.js"></script>
//  </body>
//  </html>