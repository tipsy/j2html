package com.html.to.j2html.common
import com.mohamedrejeb.ksoup.html.parser.KsoupHtmlHandler
import com.mohamedrejeb.ksoup.html.parser.KsoupHtmlParser

data class Tag(val name : String, val attributes : Map<String, String>, val children : MutableList<TagOrString>, val parent: Tag?) {
    override fun toString(): String {
        // basically just useful for debug
        return "{name=$name, attributes=$attributes, children=$children}"
    }
    fun isComment() : Boolean {
        return "comment" == name
    }
}

class TagOrString private constructor(private val tag: Tag?, private val string: String?) {
    constructor(string: String) : this(null, string)
    constructor(tag: Tag) : this(tag, null)
    fun getTag() : Tag?{
        return tag
    }
    fun getString() : String?{
        return string
    }
    override fun toString(): String {
        // basically just useful for debug
        val s1 : String = string ?: ""
        val s2 : String = tag?.toString() ?: ""
        return "$s1$s2"
    }
    fun isComment() : Boolean {
        return tag != null
            && tag.isComment()
    }
}

fun convert(input: String): String {

    val mainLevelTagsOrStrings: List<TagOrString> = extractAndSanitize(input)

    val tabStr = "  "

    var output = ""
    val howManyNonCommentTagsOrStringsWeHave = mainLevelTagsOrStrings.filter { !it.isComment() }.size
    var nonCommentIndex = 0

    for ((index, mainLevelTagOrString) in mainLevelTagsOrStrings.withIndex()) {
        val isLastNonComment = nonCommentIndex >= (howManyNonCommentTagsOrStringsWeHave - 1)
        val isLast = index >= (mainLevelTagsOrStrings.size - 1)

        if( mainLevelTagOrString.getTag() != null){

            val mainLevelTag = mainLevelTagOrString.getTag()!!
            output += outputTag(mainLevelTag, 0, tabStr)

        }
        else if( mainLevelTagOrString.getString() != null ) {

            output += outputStringValue(true, tabStr, 0, true, mainLevelTagOrString.getString()!!)

        }

        if(!mainLevelTagOrString.isComment()){
            if (!isLastNonComment) {
                output += ","
            }
            if (!isLast) {
                output += "\n"
            }
            nonCommentIndex++
        }


    }

    return output

}

private fun extractAndSanitize(input: String): List<TagOrString> {
    val mainLevelTagsOrStrings: MutableList<TagOrString> = mutableListOf()
    var currentTag: Tag? = null

    val handler = KsoupHtmlHandler
        .Builder()
        .onOpenTag { name, attributes, _ ->

            if (currentTag != null
                && !currentTag!!.isComment()
            ) {
                val newTag = Tag(name, attributes, mutableListOf(), currentTag)
                currentTag!!.children += TagOrString(newTag)
                currentTag = newTag
            } else {
                currentTag = Tag(name, attributes, mutableListOf(), null)
                mainLevelTagsOrStrings += TagOrString(currentTag!!)
            }
        }
        .onComment { comment ->
            if (comment.isNotBlank()) {
                val newTag = Tag("comment", mapOf(), mutableListOf(), currentTag)
                newTag.children += TagOrString(comment.trim())
                if (currentTag != null) {
                    currentTag!!.children += TagOrString(newTag)
                } else {
                    mainLevelTagsOrStrings += TagOrString(newTag)
                }
            }
        }
        .onText { text ->

            if (text.isNotBlank()) {
                val tagOrString = TagOrString(sanitizeWhitespace(text))

                if (currentTag != null
                    && !currentTag!!.isComment()
                ) {
                    currentTag!!.children += tagOrString
                } else {
                    mainLevelTagsOrStrings += tagOrString
                }
            }
        }
        .onCloseTag { _, _ ->
            // we go back to the parent
            currentTag = currentTag?.parent
        }
        .build()

    val parser = KsoupHtmlParser(handler)
    parser.write(input)
    parser.end()
    return mainLevelTagsOrStrings
}

fun sanitizeWhitespace(text: String): String{

    // If we don't detect any newlines we assume unformatted input and leave any excess whitespace in
    if ( ! text.contains("\n")){
        return text
    }
    else {
        // if we detect newlines, we strip out the probably unnecessary whitespace from the inputs

        val startsWithReturn = text.startsWith("\n")
        val lines = text.split("\n")
        val endsWithReturnAndMaybeSpace = lines.isNotEmpty() && lines.last().isBlank()

        var sanitized = text

        if(startsWithReturn){
            sanitized = sanitized.trimStart('\n', ' ')
        }

        if(endsWithReturnAndMaybeSpace){
            sanitized = sanitized.trimEnd('\n', ' ')
        }

        sanitized =  sanitized.replace("\\s+".toRegex(), " ")

        return sanitized

    }

}

fun outputTag(tag: Tag, indentationLevel : Int, tab : String) : String {
    var output = ""
    if (tag.isComment()) {
        // we output each line of multi-line comments on a separate java comment
        for( commentLine in tag.children[0].getString()!!.split("\n") ){
            output += tab.repeat(indentationLevel) + "// " + commentLine.trimStart(' ') + "\n"
        }

    } else if (tag.name in unsupportedTags) {
        // currently unsupported tags such as <svg>
        // we have to do a rawHtml("<unsupportedtag></unsupportedtag>")

        output += outputRawHtml(tag, tab, indentationLevel, true)


    } else if (tag.attributes.isEmpty()) {
        // no attributes -> we do the children directly in the tag declaration block
        // div(bla, bla, bla)

        output += tab.repeat(indentationLevel) + tag.name
        output += outputChildren(tag.children, indentationLevel + 1, tab, false, true)


    } else {
        // we have attributes,
        // so we do an empty declaration
        // then we do the attributes
        // then we do the children in a 'with' block
        // div().withClasses("bla", "bla").with(bla, bla, bla)

        output += tab.repeat(indentationLevel) + tag.name + "()"
        output += outputAttributes(tag.attributes, indentationLevel + 1, tab)
        if (tag.children.isNotEmpty()) {
            output += "\n"
            output += tab.repeat(indentationLevel + 1) + ".with"
            output += outputChildren(tag.children, indentationLevel + 1, tab, true, true)
        }
    }
    return output
}

fun outputRawHtml(tag : Tag, tab : String, indentationLevel: Int, mainLevelRawHtml : Boolean) : String {
    var output = ""


    if(tag.isComment()) {
        output += tab.repeat(indentationLevel)
        output += "rawHtml(\""
        output += "<-- "
        output += escapeSoThatJavaPrintsItLikeItIsInTheInput(tag.children[0].getString() ?: "")
        output += " -->"
        output += "\")"
        output += "\n"
    }
    else {
        output += tab.repeat(indentationLevel)
        output += "rawHtml(\""
        output += "<${tag.name}"
        for (attribute in tag.attributes) {
            output += escapeSoThatJavaPrintsItLikeItIsInTheInput(" ${attribute.key}=\"${attribute.value}\"")
        }

        if(tag.children.isEmpty()) {
            output += "/>"
        }
        else {
            output += ">"

            if(notAllChildrenAreText(tag.children)){
                output += "\"),"
            }

            if(tag.children.isNotEmpty()){
                output += outputChildren(tag.children, indentationLevel + 1, tab, true, false )
            }
            else{
                output += "\n"
            }

            if(notAllChildrenAreText(tag.children)){
                output += "rawHtml(\""
            }
            output += "</${tag.name}>"
        }
        output += "\")"
        // avoid double commas after the rawhtml calls
        if(!mainLevelRawHtml){
            output += ","
        }

    }

    return output
}

fun notAllChildrenAreText(children : List<TagOrString>): Boolean {
    return ! children.all{ it.getString() != null }
}

fun outputAttributes(attributes : Map<String, String>, indentationLevel : Int, tab : String): String{

    val singleAttribute = attributes.size == 1

    var output = ""
    for(attribute in attributes.entries){
        val attributeText =
            if (attribute.key == "class" )
                ".withClasses(${attribute.value.split(" ").joinToString(", ") { fragment -> "\"$fragment\"" }})"
            else if (attribute.key.startsWith("data-"))
                ".withData(\"${attribute.key.replaceFirst("data-", "")}\",\"${attribute.value}\")"
            else if (attribute.key in knownIntegerHtmlAttributes) //
                ".with${attribute.key.replaceFirstChar(Char::titlecase)}(${attribute.value.toInt()})"
            else if (attribute.key in knownBooleanHtmlAttributes)
                ".withCond${attribute.key.replaceFirstChar(Char::titlecase)}(${parseBooleanFromAttribute(attribute)})"
            else if (attribute.key in knownStringHtmlAttributes)
                ".with${attribute.key.replaceFirstChar(Char::titlecase)}(\"${attribute.value}\")"
            else
                ".attr(\"${attribute.key}\", \"${attribute.value}\")"

        if(!singleAttribute) {
            output += "\n"
            output += tab.repeat(indentationLevel)
        }
        output += attributeText

    }

    return output
}

/**
 * https://developer.mozilla.org/en-US/docs/Glossary/Boolean/HTML
 *
 * Boolean attribute (HTML)
 * A boolean attribute in HTML is an attribute that represents true or false values. If an HTML tag contains a
 * boolean attribute - no matter the value of that attribute - the attribute is set to true on that element.
 * If an HTML tag does not contain the attribute, the attribute is set to false.
 *
 * If the attribute is present, it can have one of the following values:
 *
 * no value at all, e.g. attribute
 * the empty string, e.g. attribute=""
 * attribute's name itself, with no leading or trailing whitespace, e.g. attribute="attribute"
 *
 * Addendum by me : some boolean attributes (translate, spellcheck, autocomplete, draggable, probably others)
 * use "true"/"false", "yes"/"no", or "on"/"off" so I'm parsing those as booleans too
 *
 */
fun parseBooleanFromAttribute(attribute : Map.Entry<String, String>) : Boolean {
    return "true" == attribute.value.trim()
            || "" == attribute.value
            || attribute.key == attribute.value.trim()
            || "yes" == attribute.value.trim()
            || "on" == attribute.value.trim()

}

fun outputChildren(
    children: MutableList<TagOrString>,
    indentationLevel : Int,
    tab : String,
    addTextCallsToStrings : Boolean,
    outputTheChildrenInANewParenthesesBlock : Boolean): String{

    var output = ""
    if(children.isEmpty()) {
        if(outputTheChildrenInANewParenthesesBlock){
            output += "()"
        }
    }
    else {
        val singleTextValue = children.size == 1 && children[0].getString() != null

        if(outputTheChildrenInANewParenthesesBlock) {
            output += "("
        }
        if (!singleTextValue) {
            output += "\n"
        }
        var nonCommentChildIndex = 0
        for (child: TagOrString in children) {
            val isLast = nonCommentChildIndex >= (children.filter { !it.isComment() }.size - 1)

            if (child.getTag() != null) {
                output += outputTag(child.getTag()!!, indentationLevel + 1, tab)
            } else if (child.getString() != null) {
                output += outputStringValue(singleTextValue, tab, indentationLevel, addTextCallsToStrings, child.getString()!!)
            }

            if ( (!isLast && !child.isComment())) {
                output += ", " + "\n"
            }
            else if ( !outputTheChildrenInANewParenthesesBlock ){
                // if we don't want the children in a newParenthesesBlock, we're already in a list of
                // arguments with stuff coming after us
                output += ","
            }

            if (!child.isComment()) {
                nonCommentChildIndex++
            }

        }
        if (!singleTextValue) {
            output += "\n"
            output += tab.repeat(indentationLevel - 1)
        }
        if(outputTheChildrenInANewParenthesesBlock) {
            output += ")"
        }

    }
    return output
}

private fun outputStringValue(singleTextValue: Boolean, tab: String, indentationLevel: Int, inWithBlock: Boolean, childString: String): String {
    var output = ""
    if (!singleTextValue) {
        output += tab.repeat(indentationLevel + 1)
    }
    // we're only allowed to only allowed to do p("message"), not p().withId("id").with("message")
    // so if we had attributes, we're in a with block, we
    // need a 'text' call to do p().withId("id").with(text("message"))
    // since we've kept the attributes before the values like in the html
    if (!singleTextValue || inWithBlock) {
        output += "text("
    }
    output += "\"${escapeSoThatJavaPrintsItLikeItIsInTheInput(childString)}\""

    // closing the text()
    if (!singleTextValue || inWithBlock) {
        output += ")"
    }
    return output
}

fun escapeSoThatJavaPrintsItLikeItIsInTheInput(str : String) : String{
    var output = ""
    var index = 0
    while(index < str.length){
        output += javaEscapeCode("" + str[index])
        index++
    }
    return output
}

fun javaEscapeCode(c : String): String{
    return when(c){
        "\"" -> "\\\""
        "\\" -> "\\\\"
        "\n" -> "\\n"
        "\r" -> "\\r"
        "\t" -> "\\t"
        "\b" -> "\\b"
        else -> c
    }
}

