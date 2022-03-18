package app;

import app.controllers.PageController;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

public class Main {

    public static void main(String[] args) {
        Javalin app = Javalin.create(config ->
            config.addStaticFiles("website/src/main/resources/public", Location.EXTERNAL)
        ).start(8888);

        app.get("/", PageController::serveIndex);
        app.get("/download.html", PageController::serveDownload);
        app.get("/examples.html", PageController::serveExamples);
        app.get("/news.html", PageController::serveNews);
        app.get("/404.html", PageController::serve404);

    }

}
