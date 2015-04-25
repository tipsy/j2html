public class MainTemplate {

    public static String render(String title, Tag... tags) {
        return document().render() +
                html().with(
                        head().with(
                                title(title),
                                link().withRel("stylesheet").withHref("/css/main.css")
                        ),
                        body().with(
                                header().with(
                                        h1(title)
                                ),
                                main().with(
                                        tags //content from other template
                                ),
                                footer().withText("Footer text"),
                                script().withSrc("https://code.jquery.com/jquery-2.1.3.min.js")
                        )
                ).render();
    }

}


MainTemplate.render(
    "Form test", //this is the "String title" parameter
    //Everything below here goes into the "Tag... tags" parameter
    // "..." means "varargs", which means you can add as many Tags as you like
    h2().withText("Please log in"),
    form().withMethod("post").with(
            emailInput("Email"), //see below for these three fields
            passwordInput("Password"),
            submitButton("Log in")
)


// you can create methods like these to return fully configured 
// HTML input fields/buttons (or any other kind of Tag)
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

//  OUTPUT (unminified):

//  <!DOCTYPE html>
//  <html>
//  <head>
//     <title>Form test</title>
//     <link rel="stylesheet" href="/css/main.css">
//  </head>
//  
//  <body>
//      <header>
//          <h1>Form test</h1>
//      </header>
//      <main>
//          <h2>Please log in</h2>
//          <form method="post">
//              <input type="email" id="email" name="email" placeholder="Email" required>
//              <input type="password" id="password" name="password" placeholder="Password" required>
//              <button type="submit">Log in</button>
//          </form>
//      </main>
//      <footer>Footer text</footer>
//      <script src="https://code.jquery.com/jquery-2.1.3.min.js"></script>
//  </body>
//  </html>
