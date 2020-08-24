package j2html.tags.generators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class AttributesList {

    //https://www.w3schools.com/tags/ref_attributes.asp

    static List<String> getCustomAttributesForHtmlTag(final String tagLowercase){

        final List<String> attrs = new ArrayList<>();
        for(AttrD attrD : attributesDescriptive()){
            if(
                Arrays.asList(attrD.tags).contains(tagLowercase)
            ){
                attrs.add(attrD.attr);
            }
        }
        return attrs;
    }

    static List<AttrD> attributesDescriptive() {
        return Arrays.asList(
            new AttrD("accept", true, "input"),
            //new AttrD("accept-charset","form"), //contains dashes, TODO
            //new AttrD("accesskey"), //global attribute
            new AttrD("action", true, "form"),
            //"align", not supported in HTML5
            new AttrD("alt", true, "area","img","input"),
            new AttrD("async", false, "script"),
            new AttrD("autocomplete", false, "form","input"),
            new AttrD("autofocus", false, "button","input","select","textarea"),
            new AttrD("autoplay", false, "audio","video"),
            //"bgcolor", not supported in HTMTL5
            //"border", not supported in HTML5
            new AttrD("charset", true, "meta","script"),
            new AttrD("checked", false, "input"),
            new AttrD("cite", true, "blockquote","del","ins","q"),
            //"class" already implemented in Tag.java // global attribute
            new AttrD("cols", true, "textarea"),
            new AttrD("colspan", true, "td","th"),
            new AttrD("content", true, "meta"),
            //"contenteditable" global attribute, should be in Tag.java
            new AttrD("controls", false, "audio","video"),
            new AttrD("coords", true, "area"),
            new AttrD("data", true, "object"),
            new AttrD("datetime", true, "del","ins","time"),
            new AttrD("default", false, "track"),
            new AttrD("defer", false, "script"),
            //new AttrD("dir"), //global attribute
            new AttrD("dirname", true, "input","textarea"),
            new AttrD("disabled",false, "button","fieldset","input","optgroup","option","select","textarea"),
            new AttrD("download",false, "a","area"),
            //new AttrD("draggable") global attribute, should be in Tag.java
            new AttrD("enctype", true, "form"),
            new AttrD("for", true, "label","output"),
            new AttrD("form", true, "button","fieldset","input","label","meter","object","output","select","textarea"),
            new AttrD("formaction", true, "button","input"),
            new AttrD("headers", true, "td","th"),
            new AttrD("height", true, "canvas","embed","iframe","img","input","object","video"),
            //new AttrD("hidden"), global attribute
            new AttrD("high", true, "meter"),
            new AttrD("href", true, "a","area","base","link"),
            new AttrD("hreflang", true, "a","area","link"),
            //"http-equiv", // '-' is problematic in code generation
            //"id" global attribute, should be in Tag.java
            new AttrD("ismap", true, "img"),
            new AttrD("kind", true, "track"),
            new AttrD("label", true, "track","option","optgroup"),
            //"lang" global attribute, should be in Tag.java
            new AttrD("list", true, "input"),
            new AttrD("loop", true, "audio","video"),
            new AttrD("low", true, "meter"),
            new AttrD("max", true, "input","meter","progress"),
            new AttrD("maxlength", true, "input","textarea"),
            new AttrD("media", true, "a","area","link","source","style"),
            new AttrD("method", true, "form"),
            new AttrD("min", true, "input","meter"),
            new AttrD("multiple", true, "input","select"),
            new AttrD("muted", true, "video","audio"),
            new AttrD("name", true, "button","fieldset","form","iframe","input","map","meta","object","output","param","select","textarea"),
            new AttrD("novalidate", true, "form"),
            new AttrD("onabort", true, "audio","embed","img","object","video"),
            new AttrD("onafterprint", true, "body"),
            new AttrD("onbeforeprint", true, "body"),
            new AttrD("onbeforeunload", true, "body"),
            //new AttrD("onblur"), global attribute
            new AttrD("oncanplay", true, "audio","embed","object","video"),
            new AttrD("oncanplaythrough", true, "audio","video"),
            /* a bunch of event attributes that are on all visible elements (so should be in Tag.java)
            "onchange",
            "onclick",
            "oncontextmenu",
            "oncopy",
            */
            new AttrD("oncuechange", true, "track"),
            /*
            "oncut",
            ...
            "ondrop",
             */
            new AttrD("ondurationchange", true, "audio","video"),
            new AttrD("onemptied", true, "audio","video"),
            new AttrD("onended", true, "audio","video"),
            new AttrD("onerror", true, "audio","body","embed","img","object","script","style","video"),
            //new AttrD("onfocus"),// global attribute
            new AttrD("onhashchange", true, "body"),
            // ... a bunch of event attributes visible on all elements
            new AttrD("onload", true, "body","iframe","img","input","link","script","style"),
            new AttrD("onloadeddata", true, "audio","video"),
            new AttrD("onloadedmetadata", true, "audio","video"),
            new AttrD("onloadstart", true, "audio","video"),
            // ... a bunch of event attributes visible on all elements
            new AttrD("onoffline", true, "body"),
            new AttrD("ononline", true, "body"),
            new AttrD("onpagehide", true, "body"),
            new AttrD("onpageshow", true, "body"),
            //new AttrD("onpaste"),// global attribute
            new AttrD("onpause", true, "audio","video"),
            new AttrD("onplay", true, "audio","video"),
            new AttrD("onplaying", true, "audio","video"),
            new AttrD("onpopstate", true, "body"),
            new AttrD("onprogress", true, "audio","video"),
            new AttrD("onratechange", true, "audio","video"),
            new AttrD("onreset", true, "form"),
            new AttrD("onresize", true, "body"),
            //new AttrD("onscroll"), //global attribute
            new AttrD("onsearch", true, "input"),
            new AttrD("onseeked", true, "audio","video"),
            new AttrD("onseeking", true, "audio","video"),
            //new AttrD("onselect"), //global attribute
            new AttrD("onstalled", true, "audio","video"),
            new AttrD("onstorage", true, "body"),
            new AttrD("onsubmit", true, "form"),
            new AttrD("onsuspend", true, "audio","video"),
            new AttrD("ontimeupdate", true, "audio","video"),
            new AttrD("ontoggle", true, "details"),
            new AttrD("onunload", true, "body"),
            new AttrD("onvolumechanged", true, "audio","video"),
            new AttrD("onwaiting", true, "audio","video"),
            //new AttrD("onwheel"), //global attribute
            new AttrD("open", true, "details"),
            new AttrD("optimum", true, "meter"),
            new AttrD("pattern", true, "input"),
            new AttrD("placeholder", true, "input","textarea"),
            new AttrD("poster", true, "video"),
            new AttrD("preload", true, "audio","video"),
            new AttrD("readonly", true, "input","textarea"),
            new AttrD("rel", true, "a","area","form","link"),
            new AttrD("required", false, "input","select","textarea"),
            new AttrD("reversed", true, "ol"),
            new AttrD("rows", true, "textarea"),
            new AttrD("rowspan", true, "td","th"),
            new AttrD("sandbox", true, "iframe"),
            new AttrD("scope", true, "th"),
            new AttrD("selected", true, "option"),
            new AttrD("shape", true, "area"),
            new AttrD("size", true, "input","select"),
            new AttrD("sizes", true, "img","link","source"),
            new AttrD("span", true, "col","colgroup"),
            //new AttrD("spellcheck"), //global attribute
            new AttrD("src", true, "audio","embed","iframe","img","input","script","source","track","video"),
            new AttrD("srcdoc", true, "iframe"),
            new AttrD("srclang", true, "track"),
            new AttrD("srcset", true, "img","source"),
            new AttrD("start", true, "ol"),
            new AttrD("step", true, "input"),
            //new AttrD("style"), //global attribute
            //new AttrD("tabindex"), //global attribute
            new AttrD("target", true, "a","area","base","form"),
            //new AttrD("title"), //global attribute
            //new AttrD("translate"),// global attribute
            new AttrD("type", true, "a","button","embed","input","link","menu","object","script","source","style"),
            new AttrD("usemap", true, "img","object"),
            new AttrD("value", true, "button","input","li","option","meter","progress","param"),
            new AttrD("width", true, "canvas","embed","iframe","img","input","object","video"),
            new AttrD("wrap", true, "textarea")
        );
    }
}
