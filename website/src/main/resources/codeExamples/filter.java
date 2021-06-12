// filter() is meant to be used with each(). It just calls the normal
// stream().filter() method, but hides all the boilerplate Java code
// to keep your j2html code neat
body().with(
    div().withId("employees").with(
        p("Some sibling element"),
        each(filter(employees, employee -> employee != null), employee ->
            div().withClass("employee").with(
                h2(employee.getName()),
                img().withSrc(employee.getImgPath()),
                p(employee.getTitle())
            )
        )
    )
)
