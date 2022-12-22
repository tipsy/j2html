package com.j2html.codegen;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Mojo(name = "generate-source-files", defaultPhase = LifecyclePhase.GENERATE_SOURCES)
public class J2htmlCodeGeneratorMojo extends AbstractMojo {

    @Parameter(defaultValue = "${project}", required = true, readonly = true)
    MavenProject project;

    @Parameter(property = "modelFile", required = true)
    String modelFile;

    @Parameter(property = "attributePackage", required = true)
    String attributePackage;

    @Parameter(property = "tagPackage", required = true)
    String tagPackage;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().debug("Model File: " + modelFile);
        getLog().debug("Attribute Package: " + attributePackage);
        getLog().debug("Tag Package: " + tagPackage);

        String outputDirectory = project.getBuild().getDirectory() + "/generated-sources/j2html-codegen";
        project.addCompileSourceRoot(outputDirectory);
        getLog().debug("Generating J2Html sources in: " + outputDirectory);

        String definitions;
        try {
            definitions = new String(Files.readAllBytes(Paths.get(modelFile)));
        } catch (IOException e) {
            throw new MojoFailureException("Unable to locate model file: " + modelFile, e);
        }

        Model model = new Model();
        try {
            Parser.parse(definitions, model);
        }catch (RuntimeException e){
            throw new MojoFailureException("Unable to parse model file.", e);
        }

        try {
            Generator.generate(
                Paths.get(outputDirectory).toAbsolutePath(),
                attributePackage,
                tagPackage,
                model
            );
        } catch (IOException e) {
            throw new MojoFailureException("Failed to generate source files.", e);
        }
    }
}
