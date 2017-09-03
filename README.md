![](https://img.shields.io/travis/tipsy/j2html.svg) 
![](https://img.shields.io/github/license/tipsy/j2html.svg)
![](https://img.shields.io/maven-central/v/com.j2html/j2html.svg)

# j2html
Java to HTML generator. Enjoy typesafe HTML generation.

The project webpage is [j2html.com](http://j2html.com).

## Getting started
### Add the maven dependency
```xml
<dependency>
    <groupId>com.j2html</groupId>
    <artifactId>j2html</artifactId>
    <version>1.2.0</version>
</dependency>
```
### OR the gradle dependency
```
compile 'com.j2html:j2html:1.2.0'
```

### Import TagCreator and start building HTML
```java
import static j2html.TagCreator.*;

public class Main {
    public static void main(String[] args) {
        body().with(
            h1("Heading!").withClass("example"),
            img().withSrc("img/hello.png")
        ).render();
    }
}
```
The above Java will result in the following HTML:
```html
<body>
    <h1 class="example">Heading!</h1>
    <img src="img/hello.png">
</body>
```

Find more examples at http://j2html.com/examples.html
