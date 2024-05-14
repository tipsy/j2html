package app;

import app.controllers.PageController;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class Main {
    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args)  {
        String path = "./src/main/resources/public";
        File file = new File(path);

        try {
            LOG.info("static files served from location: {}, ready {} ", file.getCanonicalFile(), file.exists());
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }

        Javalin app = Javalin.create(
            config -> config.staticFiles.add(path, Location.EXTERNAL)
        ).start(8888);

        app.get("/", PageController::serveIndex);
        app.get("/download.html", PageController::serveDownload);
        app.get("/examples.html", PageController::serveExamples);
        app.get("/news.html", PageController::serveNews);
        app.get("/404.html", PageController::serve404);

    }

}
