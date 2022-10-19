List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 9, 10);
...
table(attr("#table-example"),
    tbody(
        each(numbers, i -> tr(
            each(numbers, j -> td(
                String.valueOf(i * j)
            ))
        ))
    )
)