body(
    div(attrs("#employees"),
        employees.stream().map(employee ->
            div(attrs(".employee"),
                h2(employee.getName()),
                img().withSrc(employee.getImgPath()),
                p(employee.getTitle())
            )
        ).toArray(ContainerTag[]::new)
    )
)
