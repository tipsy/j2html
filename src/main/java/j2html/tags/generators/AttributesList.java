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

    static List<String> attributes(){
        return attributesDescriptive().stream().map(a->a.attr).collect(Collectors.toList());
    }

    static List<AttrD> attributesDescriptive() {
        return Arrays.asList(
            new AttrD("accept", "input"),
            //new AttrD("accept-charset","form"), //contains dashes, TODO
            //new AttrD("accesskey"), //global attribute
            new AttrD("action", "form"),
            //"align", not supported in HTML5
            new AttrD("alt", "area","img","input"),
            new AttrD("async","script"),
            new AttrD("autocomplete","form","input"),
            new AttrD("autofocus","button","input","select","textarea"),
            new AttrD("autoplay","audio","video"),
            //"bgcolor", not supported in HTMTL5
            //"border", not supported in HTML5
            new AttrD("charset","meta","script"),
            new AttrD("checked","input"),
            new AttrD("cite","blockquote","del","ins","q"),
            //"class" already implemented in Tag.java // global attribute
            new AttrD("cols","textarea"),
            new AttrD("colspan","td","th"),
            new AttrD("content","meta"),
            //"contenteditable" global attribute, should be in Tag.java
            new AttrD("controls","audio","video"),
            new AttrD("coords","area"),
            new AttrD("data","object"),
            new AttrD("datetime","del","ins","time"),
            new AttrD("default","track"),
            new AttrD("defer","script"),
            //new AttrD("dir"), //global attribute
            new AttrD("dirname","input","textarea"),
            //new AttrD("disabled","button","fieldset","input","optgroup","option","select","textarea"), manually implemented
            //new AttrD("download","a","area") manually implemented
            //new AttrD("draggable") global attribute, should be in Tag.java
            new AttrD("enctype", "form"),
            new AttrD("for","label","output"),
            new AttrD("form","button","fieldset","input","label","meter","object","output","select","textarea"),
            new AttrD("formaction","button","input"),
            new AttrD("headers","td","th"),
            new AttrD("height","canvas","embed","iframe","img","input","object","video"),
            //new AttrD("hidden"), global attribute
            new AttrD("high","meter"),
            new AttrD("href","a","area","base","link"),
            new AttrD("hreflang","a","area","link"),
            //"http-equiv", // '-' is problematic in code generation
            //"id" global attribute, should be in Tag.java
            new AttrD("ismap", "img"),
            new AttrD("kind","track"),
            new AttrD("label","track","option","optgroup"),
            //"lang" global attribute, should be in Tag.java
            new AttrD("list","input"),
            new AttrD("loop","audio","video"),
            new AttrD("low","meter"),
            new AttrD("max","input","meter","progress"),
            new AttrD("maxlength","input","textarea"),
            new AttrD("media","a","area","link","source","style"),
            new AttrD("method","form"),
            new AttrD("min","input","meter"),
            new AttrD("multiple","input","select"),
            new AttrD("muted","video","audio"),
            new AttrD("name","button","fieldset","form","iframe","input","map","meta","object","output","param","select","textarea"),
            new AttrD("novalidate","form"),
            new AttrD("onabort","audio","embed","img","object","video"),
            new AttrD("onafterprint","body"),
            new AttrD("onbeforeprint","body"),
            new AttrD("onbeforeunload","body"),
            //new AttrD("onblur"), global attribute
            new AttrD("oncanplay", "audio","embed","object","video"),
            new AttrD("oncanplaythrough","audio","video"),
            /* a bunch of event attributes that are on all visible elements (so should be in Tag.java)
            "onchange",
            "onclick",
            "oncontextmenu",
            "oncopy",
            */
            new AttrD("oncuechange","track"),
            /*
            "oncut",
            ...
            "ondrop",
             */
            new AttrD("ondurationchange","audio","video"),
            new AttrD("onemptied","audio","video"),
            new AttrD("onended","audio","video"),
            new AttrD("onerror","audio","body","embed","img","object","script","style","video"),
            //new AttrD("onfocus"),// global attribute
            new AttrD("onhashchange","body"),
            // ... a bunch of event attributes visible on all elements
            new AttrD("onload","body","iframe","img","input","link","script","style"),
            new AttrD("onloadeddata","audio","video"),
            new AttrD("onloadedmetadata","audio","video"),
            new AttrD("onloadstart", "audio","video"),
            // ... a bunch of event attributes visible on all elements
            new AttrD("onoffline","body"),
            new AttrD("ononline","body"),
            new AttrD("onpagehide","body"),
            new AttrD("onpageshow","body"),
            //new AttrD("onpaste"),// global attribute
            new AttrD("onpause","audio","video"),
            new AttrD("onplay","audio","video"),
            new AttrD("onplaying","audio","video"),
            new AttrD("onpopstate","body"),
            new AttrD("onprogress","audio","video"),
            new AttrD("onratechange","audio","video"),
            new AttrD("onreset","form"),
            new AttrD("onresize","body"),
            //new AttrD("onscroll"), //global attribute
            new AttrD("onsearch","input"),
            new AttrD("onseeked","audio","video"),
            new AttrD("onseeking","audio","video"),
            //new AttrD("onselect"), //global attribute
            new AttrD("onstalled","audio","video"),
            new AttrD("onstorage","body"),
            new AttrD("onsubmit","form"),
            new AttrD("onsuspend","audio","video"),
            new AttrD("ontimeupdate","audio","video"),
            new AttrD("ontoggle","details"),
            new AttrD("onunload","body"),
            new AttrD("onvolumechanged","audio","video"),
            new AttrD("onwaiting","audio","video"),
            //new AttrD("onwheel"), //global attribute
            new AttrD("open","details"),
            new AttrD("optimum","meter"),
            new AttrD("pattern","input"),
            new AttrD("placeholder","input","textarea"),
            new AttrD("poster","video"),
            new AttrD("preload","audio","video"),
            new AttrD("readonly","input","textarea"),
            new AttrD("rel","a","area","form","link"),
            new AttrD("required","input","select","textarea"),
            new AttrD("reversed","ol"),
            new AttrD("rows","textarea"),
            new AttrD("rowspan","td","th"),
            new AttrD("sandbox","iframe"),
            new AttrD("scope","th"),
            new AttrD("selected","option"),
            new AttrD("shape","area"),
            new AttrD("size","input","select"),
            new AttrD("sizes","img","link","source"),
            new AttrD("span","col","colgroup"),
            //new AttrD("spellcheck"), //global attribute
            new AttrD("src","audio","embed","iframe","img","input","script","source","track","video"),
            new AttrD("srcdoc","iframe"),
            new AttrD("srclang","track"),
            new AttrD("srcset","img","source"),
            new AttrD("start","ol"),
            new AttrD("step","input"),
            //new AttrD("style"), //global attribute
            //new AttrD("tabindex"), //global attribute
            new AttrD("target", "a","area","base","form"),
            //new AttrD("title"), //global attribute
            //new AttrD("translate"),// global attribute
            new AttrD("type","a","button","embed","input","link","menu","object","script","source","style"),
            new AttrD("usemap","img","object"),
            new AttrD("value","button","input","li","option","meter","progress","param"),
            new AttrD("width","canvas","embed","iframe","img","input","object","video"),
            new AttrD("wrap","textarea")
        );
    }
}
