import com.html.to.j2html.common.convert

@JsExport
@OptIn(kotlin.js.ExperimentalJsExport::class)
@JsName("j2htmlconvert")
fun j2htmlconvert(input: String): String {

    return convert(input)
}
