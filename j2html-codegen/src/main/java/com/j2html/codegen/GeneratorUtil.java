package com.j2html.codegen;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class GeneratorUtil {

    public static final void deleteAllFilesInDir(final Path dir) throws IOException {
        for(final File file : dir.toFile().listFiles()){
            System.out.println("deleting " + file.toPath());
            Files.delete(file.toPath());
        }
    }
}
