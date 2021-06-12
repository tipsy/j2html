# Contributing

This Project is currently accepting Contributions.
Have a look at the Issues for Inspiration.

## Requirements

- JDK >= 1.8 (see pom.xml)
- Maven

## Project Goals

As stated in the **README**, the Goal of this Project is to enjoy typesafe, performant HTML generation in Java.

For preventing performance regressions, there are Tests.

## Coding Style 

There is a `.editorconfig` and a `eclipse_formatting_profile.xml`

What sticks out is that end of line is CRLF `'\r\n'` for this Project.

This means that if you're on Linux `'\n'`, you have to configure git to handle this
correctly so that you have the correct EOL in your working directory,
and the EOL is also correct in the repository itself.

For this purpose, j2html has a `.gitattributes` file.

[Guide to configuring EOL with git](https://docs.github.com/en/github/using-git/configuring-git-to-handle-line-endings)

If you are on Windows, there should be no Problems.

### Reformatting of generated Java Code

As this Projects makes use of Code-Generation techniques in order to generate a more typesafe API without too much manual Work,
there is the `code_gen/` directory which contains everything needed to generate the code.

For simplicity (and also to avoid extra dependencies), they do not format the code correctly. 

So if you change the Code-Generation Code, you may need to reformat the generated files to fit the Coding Style.

## Contribution Workflow

The workflow (most of the time) consists of:

- Comment (on Issue or PR) to find out what needs Work
- Get Feedback on your Ideas
- Fork this repo
- Open a PR 
- Adjust the PR until it is merged or discarded

## Project Architecture

### library/src/main/java/j2html/TagCreator.java

This is **the** central class in J2HTML. It provides the methods 
for users of J2HTML to generate all HTML Tags.
It contains methods like
```
public static HtmlTag html(DomContent... dc) {
    return new HtmlTag().with(dc);
}
```
which can be used in Projects using this dependency as 
```
html(
    head(
        script("https://example.com/my/js/files.js")
    ),
    body(
        div(
            h1("Hello World")
        ).withClasses("container")
    )
)
```

### How are the different HTML Tags implemented?

Each HTML Tag has it's own class, which makes it possible for each Tag to have
the correct Attributes and Methods to set those Attributes.

The classes are located in `library/src/main/java/j2html/tags/specialized` and follow the naming convention `tag_name + 'Tag.java'`, e.g. `BodyTag.java`.
Notice that the first letter of the Tag is in uppercase.

Each Tag-specific class `implements` interfaces which correspond to the Attributes that can be set on these Tags.

For Reference which Tags support which Attributes, see [HTML Attribute Reference](https://www.w3schools.com/tags/ref_attributes.asp).

For Example, `ButtonTag` might implement `IType<ButtonTag>` which says it can have an Attribute `type`, which may later show up like `<button type="submit"></button>`.

### How are the Attributes of HTML Tags implemented?

Each Attribute has it's own interface in `src/main/java/j2html/tags/attributes/` and follows the naming convention `"I" + attribute_name + '.java'`, e.g. `IAccept.java`. Notice that the first letter of the Attribute is in uppercase.

Dissecting `IAccept.java`:

```
public interface IAccept<T extends Tag> extends IInstance<T> {
    default T withAccept(final String accept_) {
        return self().attr("accept", accept_);
    }

    default T withCondAccept(final boolean enable, final String accept_) {
        if (enable) {
            self().attr("accept", accept_);
        }
        return self();
    }
}
```

As you can see, **IAccept** extends `IInstance<T>` which provides only the `self()` Method to access an instance of type `T`.
All attribute-specific interfaces extend `IInstance<T>`.

```
public interface IInstance<T> {
    default T self() {  return (T) this;  }
}
```

`IInstance<T>` is cheating the type system because `self()` returns an instance of type `T`, but the implementing class
technically does not have to supply it's own type as the type argument. But by convention, in this Project, the implementing class 
always supplies it's own type as the type argument.

But in `default` methods in interfaces there is AFAIK no way to obtain the type of the class that is implementing the interface.
If you find a way, that would be a great PR.

### Special classes/interfaces besides TagCreator.java

There are 3 classes which contain code-generating methods in `code_gen/src/main/java/j2html_codegen/generators/`:

- `AttributeInterfaceCodeGenerator.java` (generating the interfaces for the attributes)
- `SpecializedTagClassCodeGenerator.java` (generating the classes for the tags)
- `TagCreatorCodeGenerator.java` (generating some contents of `TagCreator.java`)

### Other special classes / interfaces in J2HTML

- **Tag.java** is the base class for every tag and extends DomContent
- **EmptyTag.java** is the base class for all Tags which have no contents
- **ContainerTag.java** is the base class for all Tags which can contain other tags
- **DomContent.java** 

### How is the Code generation for the Attribute specific Interfaces parameterized?

Attributes differ in their 'type' . Some of them can be set with numbers (which are converted into strings in the html). 
Others can only be set or not set, others still have 3 states: set, unset, and not present. 
To model these propertise, a single Attribute can be described by an instance of **AttrD.java**.

`library/src/main/java/j2html/tags/generators/AttributesList.java`  contains the different Attributes, their properties,
and the Tags they can be set on. It is the starting point for adding new Attributes and customizing their properties.


