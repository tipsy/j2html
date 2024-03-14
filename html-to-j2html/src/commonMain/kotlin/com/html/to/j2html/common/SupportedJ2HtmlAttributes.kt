package com.html.to.j2html.common
/**
 * attributes that require specific treatment
 */
fun specialAttributes() = listOf (
    "class", // withClasses(val1, val2, val3) for each space separated token // DONE
    "data-*", // the * is the key and the value is the value, and becomes .withData("key", "value") // DONE

)

/**
 * we produce raw html for these
 */
val unsupportedTags : List<String> = listOf(
    "svg",
    "path",
)

/**
 * Not much to do with those, just taking them out of the supported list without losing track of them
 *
 * we default to attr("key", "value") for those
 *
 */
val unsupportedAttributes : List<String> = listOf(
        "accept-charset", // valid html5
        "http-equiv", // valid html5
        "inputmode", // valid html5
        "popovertarget", // valid html5
        "popovertargetaction", // valid html5
        "inert", // valid html5
        "enterkeyhint", // valid html5
        "onblur", // valid html5
        "onchange", // valid html5
        "onclick", // valid html5
        "oncontextmenu", // valid html5
        "oncopy", // valid html5
        "oncut", // valid html5
        "ondblclick", // valid html5
        "ondrag", // valid html5
        "ondragend", // valid html5
        "ondragenter", // valid html5
        "ondragleave", // valid html5
        "ondragover", // valid html5
        "ondragstart", // valid html5
        "ondrop", // valid html5
        "onfocus", // valid html5
        "oninput", // valid html5
        "oninvalid", // valid html5
        "onkeydown", // valid html5
        "onkeypress", // valid html5
        "onkeyup", // valid html5
        "onmousedown", // valid html5
        "onmousemove", // valid html5
        "onmouseout", // valid html5
        "onmouseover", // valid html5
        "onmouseup", // valid html5
        "onmousewheel", // valid html5
        "onpaste", // valid html5
        "onscroll", // valid html5
        "onselect", // valid html5
        "onwheel", // valid html5
        "popover", // valid html5

)

/**
 * we default to attr("key", "value") for those
 *
 */
val buggedAttributes : List<String> = listOf(
    "autocomplete", // input().withCondAutocomplete(true) but mozilla docs say it's not just a boolean: valid values are "off", "on", "name", "email", etc
    "onvolumechange", // audio().withOnvolumechanged("value") exists instead of withOnvolumechange
)

/**
 * list of HTML integer attributes
 *
 * these have been tested to exist in j2html with the associated comment code
 */
val knownIntegerHtmlAttributes : List<String> = listOf(
    "tabindex", // div().withTabindex(5) // tabindex
)

/**
 * list of HTML boolean attributes copied on 28-02-2024 from
 * https://meiert.com/en/blog/boolean-attributes-of-html/
 * with a few that I found myself during tests, and a few taken out because unsupported
 *
 * these have been tested to exist in j2html with the associated comment code
 */
val knownBooleanHtmlAttributes : List<String> = listOf(
    "async", // script().withCondAsync(true), // async
    "autofocus", // input().withCondAutofocus(true), // autofocus
    "autoplay", // video().withCondAutoplay(true), // autoplay
    "checked", // input().withCondChecked(true), // checked
    "controls", // audio().withCondControls(true), // controls
    "default", //  track().withCondDefault(true), // default
    "defer", // script().withCondDefer(true), // defer
    "disabled", // input().withCondDisabled(true), // disabled
    "ismap", // img().withCondIsmap(true), // ismap
    "loop", // audio().withCondLoop(true), // loop
    "multiple", // select().withCondMultiple(true), // multiple
    "muted", // video().withCondMuted(true), // muted
    "novalidate", // form().withCondNovalidate(true), // novalidate
    "open", // details().withCondOpen(true), // open
    "readonly", // input().withCondReadonly(true), // readonly
    "required", // select().withCondRequired(true), // required
    "reversed", // ol().withCondReversed(true), // reversed
    "selected", // option().withCondSelected(true), // selected
    // boolean attributes I found during tests:
    "contenteditable", // div().withCondContenteditable(true) // contenteditable
    "download", // area().withCondDownload(true), // download
    "draggable", // img().withCondDraggable(true), // draggable
    "hidden", // img().withCondHidden(true), // hidden
    "sandbox", // iframe().withCondSandbox(true), // sandbox
    "spellcheck", // input().withCondSpellcheck(true), // spellcheck
    "translate", // input().withCondTranslate(true), // translate
)


/**
 * these have been tested to exist in j2html with the associated comment code
 */
val knownStringHtmlAttributes : List<String> = listOf(
        "accept", // input().withAccept("value"), // accept
        "accesskey", // div().withAccesskey("value"), // accesskey
        "action", // form().withAction("value"), // action
        "alt", // input().withAlt("value"), // alt
        "charset", // meta().withCharset("value"), // charset
        "cite", // del().withCite("value"), // cite
        "cols", // textarea().withCols("value"), // cols
        "colspan", // td().withColspan("value"), // colspan
        "content", // meta().withContent("value"), // content
        "coords", // area().withCoords("value"), // coords
        "datetime", // time().withDatetime("value"), // datetime
        "dir", // div().withDir("value"), // dir
        "dirname", // input().withDirname("value"), // dirname
        "enctype", // form().withEnctype("value"), // enctype
        "for", // label().withFor("value"), // for
        "form", // input().withForm("value"), // form
        "formaction", // button().withFormaction("value"), // formaction
        "headers", // td().withHeaders("value"), // headers
        "height", // canvas().withHeight("value"), // height
        "high", // meter().withHigh("value"), // high
        "href", // a().withHref("value"), // href
        "hreflang", // a().withHreflang("value"), // hreflang
        "id", // div().withId("value"), // id
        "kind", // track().withKind("value"), // kind
        "label", // option().withLabel("value"), // label
        "lang", // div().withLang("value"), // lang
        "list", // input().withList("value"), // list
        "low", // meter().withLow("value"), // low
        "max", // input().withMax("value"), // max
        "maxlength", // input().withMaxlength("value"), // maxlength
        "media", // a().withMedia("value"), // media
        "method", // form().withMethod("value"), // method
        "min", // input().withMin("value"), // min
        "name", // input().withName("value"), // name
        "optimum", // meter().withOptimum("value"), // optimum
        "onabort", // img().withOnabort("value"), // onabort
        "onafterprint", // body().withOnafterprint("value"), // onafterprint
        "onbeforeprint", // body().withOnbeforeprint("value"), // onbeforeprint
        "onbeforeunload", // body().withOnbeforeunload("value"), // onbeforeunload
        "oncanplay", // video().withOncanplay("value"), // oncanplay
        "oncanplaythrough", // video().withOncanplaythrough("value"), // oncanplaythrough
        "oncuechange", // track().withOncuechange("value"), // oncuechange
        "ondurationchange", // video().withOndurationchange("value"), // ondurationchange
        "onemptied", // video().withOnemptied("value"), // onemptied
        "onended", // video().withOnended("value"), // onended
        "onerror", // video().withOnerror("value"), // onerror
        "onhashchange", // body().withOnhashchange("value"), // onhashchange
        "onload", // input().withOnload("value"), // onload
        "onloadeddata", // video().withOnloadeddata("value"), // onloadeddata
        "onloadedmetadata", // video().withOnloadedmetadata("value"), // onloadedmetadata
        "onloadstart", // video().withOnloadstart("value"), // onloadstart
        "onoffline", // body().withOnoffline("value"), // onoffline
        "ononline", // body().withOnonline("value"), // ononline
        "onpagehide", // body().withOnpagehide("value"), // onpagehide
        "onpageshow", // body().withOnpageshow("value"), // onpageshow
        "onpause", // video().withOnpause("value"), // onpause
        "onplay", // video().withOnplay("value"), // onplay
        "onplaying", // video().withOnplaying("value"), // onplaying
        "onpopstate", // body().withOnpopstate("value"), // onpopstate
        "onprogress", // video().withOnprogress("value"), // onprogress
        "onratechange", // video().withOnratechange("value"), // onratechange
        "onreset", // form().withOnreset("value"), // onreset
        "onresize", // body().withOnresize("value"), // onresize
        "onsearch", // input().withOnsearch("value"), // onsearch
        "onseeked", // video().withOnseeked("value"), // onseeked
        "onseeking", // video().withOnseeking("value"), // onseeking
        "onstalled", // video().withOnstalled("value"), // onstalled
        "onstorage", // body().withOnstorage("value"), // onstorage
        "onsubmit", // form().withOnsubmit("value"), // onsubmit
        "onsuspend", // video().withOnsuspend("value"), // onsuspend
        "ontimeupdate", // video().withOntimeupdate("value"), // ontimeupdate
        "ontoggle", // details().withOntoggle("value"), // ontoggle
        "onunload", // body().withOnunload("value"), // onunload
        "onwaiting", // video().withOnwaiting("value"), // onwaiting
        "pattern", // input().withPattern("value"), // pattern
        "placeholder", // input().withPlaceholder("value"), // placeholder
        "poster", // video().withPoster("value"), // poster
        "preload", // video().withPreload("value"), // preload
        "rel", // form().withRel("value"), // rel
        "rows", // textarea().withRows("value"), // rows
        "rowspan", // td().withRowspan("value"), // rowspan
        "scope", // th().withScope("value"), // scope
        "shape", // area().withShape("value"), // shape
        "size", // input().withSize("value"), // size
        "sizes", // img().withSizes("value"), // sizes
        "span", // col().withSpan("value"), // span
        "src", // input().withSrc("value"), // src
        "srcdoc", // iframe().withSrcdoc("value"), // srcdoc
        "srclang", // track().withSrclang("value"), // srclang
        "srcset", // img().withSrcset("value"), // srcset
        "start", // ol().withStart("value"), // start
        "step", // input().withStep("value"), // step
        "style", // div().withStyle("value"), // style
        "target", // form().withTarget("value"), // target
        "title", // div().withTitle("value"), // title
        "type", // input().withType("value"), // type
        "usemap", // img().withUsemap("value"), // usemap
        "value", // input().withValue("value"), // value
        "width", // input().withWidth("value"), // width
        "wrap" // textarea().withWrap("value") // wrap
)

