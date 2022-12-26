public class MainView {
    public static String render(String pageTitle, Tag... tags) {
        return document(
            html(
                head(
                    title(pageTitle)
                ),
                body(
                    header(
                        ...
                    ),
                    main(
                        tags //the view from the partials example
                    ),
                    footer(
                        ...
                    )
                )
            )
        );
    }
}

MainView.render(
    "Signup page",
    h1("Please sign up"),
    form().withMethod("post").with(
        emailInput("Email address"),
        choosePasswordInput("Choose Password"),
        repeatPasswordInput("Repeat Password"),
        submitButton("Sign up")
    )
);
