//Java Code
html().with(
    head().with(
        title("Title"),
        link().withRel("stylesheet").withHref("/css/main.css")
    ),
    body().with(
        main().with(
            h1("Heading!")
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
