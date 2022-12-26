// each() lets you iterate through a collection and returns the generated HTML
// as a DomContent object, meaning you can add siblings, which is not possible
// using the stream api in the previous example
body(
    div(attrs("#employees"),
        p("Some sibling element"),
        each(employees, employee ->
            div(attrs(".employee"),
                h2(employee.getName()),
                img().withSrc(employee.getImgPath()),
                p(employee.getTitle())
            )
        )
    )
)
