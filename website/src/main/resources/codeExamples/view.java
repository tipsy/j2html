h1("Please sign up"),
form().withMethod("post").with(
    emailInput("Email address"),
    choosePasswordInput("Choose Password"),
    repeatPasswordInput("Repeat Password"),
    submitButton("Sign up")
)
