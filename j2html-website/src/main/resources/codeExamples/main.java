public class MainView {
    public static String render(String pageTitle, Tag... tags) {
        return document().render() +
            html().with(
                head().with(
                    title(pageTitle)
                ),
                body().with(
                    header().with(
                        ...
                    ),
                    main().with(
                        tags //the view from the partials example
                    ),
                    footer().with(
                        ...
                    )
                )
            ).render();
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
