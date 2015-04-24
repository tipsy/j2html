//Java Code
html().with(
    head().with(
        title().withText("Title"),
        link().withRel("stylesheet").withHref("/css/main.css")
    ),
    body().with(
        main().with(
            h1().withText("Heading!")
        )
    )
).render();


/* Rendered HTML
<html>
    <head>
        <title>Title</title>
        <link rel="stylesheet" href="/css/main.css">
    </head>
    <body>
        <main>
            <h1>Heading!</h1>
        </main>
    </body>
<html>
*/