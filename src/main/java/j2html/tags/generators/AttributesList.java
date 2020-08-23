package j2html.tags.generators;

import java.util.Arrays;
import java.util.List;

public final class AttributesList {

    static List<String> attributes() {
        return Arrays.asList(
            "accept",
            //"accept-charset", contains dashes, TODO
            "accesskey",
            "action",
            //"align", not supported in HTML5
            "alt",
            "async",
            "autocomplete",
            "autofocus",
            "autoplay",
            //"bgcolor", not supported in HTMTL5
            //"border", not supported in HTML5
            "charset",
            "checked",
            "cite",
            //"class" already implemented in Tag.java
            "cols",
            "colspan",
            "content",
            //"contenteditable" global attribute, should be in Tag.java
            "controls",
            "coords",
            "data",
            "datetime",
            "default",
            "defer",
            "dir",
            "dirname",
            //"disabled", manually implemented
            //"download" manually implemented
            //"draggable" global attribute, should be in Tag.java
            "enctype",
            "for",
            "form",
            "formaction",
            "headers",
            "height",
            "hidden",
            "high",
            "href",
            "hreflang",
            //"http-equiv", // '-' is problematic in code generation
            //"id" global attribute, should be in Tag.java
            "ismap",
            "kind",
            "label",
            //"lang" global attribute, should be in Tag.java
            "list",
            "loop",
            "low",
            "max",
            "maxlength",
            "media",
            "method",
            "min",
            "multiple",
            "muted",
            "name",
            "novalidate",
            "onabort",
            "onafterprint",
            "onbeforeprint",
            "onbeforeunload",
            "onblur",
            "oncanplay",
            "oncanplaythrough",
            /* a bunch of event attributes that are on all visible elements (so should be in Tag.java)
            "onchange",
            ""

             */
            "ondurationchange",
            "onemptied",
            "onended",
            "onerror",
            //"onfocus" global attribute
            "onhashchange",
            // ... a bunch of event attributes visible on all elements
            "onload",
            "onloadeddata",
            "onloadedmetadata",
            "onloadstart",
            // ... a bunch of event attributes visible on all elements
            "onoffline",
            "ononline",
            "onpagehide",
            "onpageshow",
            //"onpaste" global attribute
            "onpause",
            "onplay",
            "onplaying",
            "onpopstate",
            "onprogress",
            "onratechange",
            "onreset",
            "onresize",
            "onscroll",
            "onsearch",
            "onseeked",
            "onseeking",
            "onselect",
            "onstalled",
            "onstorage",
            "onsubmit",
            "onsuspend",
            "ontimeupdate",
            "ontoggle",
            "onunload",
            "onvolumechanged",
            "onwaiting",
            "onwheel",
            "open",
            "optimum",
            "pattern",
            "placeholder",
            "poster",
            "preload",
            "readonly",
            "rel",
            "required",
            "reversed",
            "rows",
            "rowspan",
            "sandbox",
            "scope",
            "selected",
            "shape",
            "size",
            "sizes",
            "span",
            "spellcheck",
            "src",
            "srcdoc",
            "srclang",
            "srcset",
            "start",
            "step",
            //"style", global attribute
            //"tabindex", global attribute
            "target",
            //"title", global attribute
            //"translate" global attribute
            "type",
            "usemap",
            "value",
            "width",
            "wrap"
        );
    }
}
