List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 9, 10);
...
table().withId("table-example").with(
    tbody().with(
        each(numbers, i -> tr().with(
            each(numbers, j -> td().with(
                String.valueOf(i * j)
            ))
        ))
    )
)
