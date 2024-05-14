# Getting started 

### Build all projects:
- `mvn clean install`

### Core project
- The core project is situated in the `j2html` directory. When building, source code is generated using the maven plugin from project `j2html-codegen`

### Extra projects
- `j2html-website` - the project website (using j2html code) that runs via Javalin (https://javalin.io/)
- `j2html-codegen` - maven plugin for generating Java sources from a configured collection of tags (in a .model file).
- `j2html-mathml` - a mathml extension

### Hints when using Intellij: 
- Mark directory `j2html/target/generated-sources/j2html-codegen` as Generated Sources Root
- Mark directory `j2html-ext-mathml/target/generated-sources/j2html-codegen` as Generated Sources Root

### Security check on dependencies
- Type `mvn org.owasp:dependency-check-maven:6.5.3:aggregate` and wait for the generated security report


