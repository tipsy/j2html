public static Tag enterPasswordInput(String placeholder) {
    return passwordInput("enterPassword", placeholder);
}

public static Tag choosePasswordInput(String placeholder) {
    return passwordInput("choosePassword", placeholder);
}

public static Tag repeatPasswordInput(String placeholder) {
    return passwordInput("repeatPassword", placeholder);
}

public static Tag passwordInput(String identifier, String placeholder) {
    return input()
        .withType("password")
        .withId(identifier)
        .withName(identifier)
        .withPlaceholder(placeholder)
        .isRequired();
}

public static Tag emailInput(String placeholder) {
    return input()
        .withType("email")
        .withId("email")
        .withName("email")
        .withPlaceholder(placeholder)
        .isRequired();
}

public static Tag submitButton(String text) {
    return button().withType("submit").withText(text);
}
