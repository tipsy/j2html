package j2html.attributes;

import j2html.tags.Tag;

public class Attr {

    public static class Shortform {
        String id;
        String classes;

        private Shortform(String id, String classes) {
            this.id = id;
            this.classes = classes;
        }
    }

    public static Shortform attrs(String shortformAttributes) {
        if (!shortformAttributes.contains(".") && !shortformAttributes.contains(("#"))) {
            throw new IllegalArgumentException("String must contain either id (#) or class (.)");
        }
        if (shortformAttributes.split("#").length > 2) {
            throw new IllegalArgumentException("Only one id (#) allowed");
        }
        String id = "";
        StringBuilder classes = new StringBuilder();
        for (String s : shortformAttributes.split("\\.")) {
            if (s.contains("#")) {
                id = s.replace("#", "");
            } else {
                classes.append(s).append(" ");
            }
        }
        return new Shortform(id.trim(), classes.toString().trim());
    }

    @SuppressWarnings("unchecked")
    public static <T> T addTo(Tag<? extends Tag> tag, Attr.Shortform shortform) {
        if (!"".equals(shortform.id) && !"".equals(shortform.classes)) {
            return (T) tag.withId(shortform.id).withClass(shortform.classes);
        }
        if (!"".equals(shortform.id)) {
            return (T) tag.withId(shortform.id);
        }
        if (!"".equals(shortform.classes)) {
            return (T) tag.withClass(shortform.classes);
        }
        return (T) tag;
    }

    private Attr() {
    }

    public static final String ACCEPT = "accept";
    public static final String ACCEPT_CHARSET = "accept-charset";
    public static final String ACCESSKEY = "accesskey";
    public static final String ACTION = "action";
    public static final String ALIGN = "align";
    public static final String ALT = "alt";
    public static final String ASYNC = "async";
    public static final String AUTOCOMPLETE = "autocomplete";
    public static final String AUTOFOCUS = "autofocus";
    public static final String AUTOPLAY = "autoplay";
    public static final String AUTOSAVE = "autosave";
    public static final String BORDER = "border";
    public static final String BUFFERED = "buffered";
    public static final String CHALLENGE = "challenge";
    public static final String CHARSET = "charset";
    public static final String CHECKED = "checked";
    public static final String CITE = "cite";
    public static final String CLASS = "class";
    public static final String COLOR = "color";
    public static final String COLS = "cols";
    public static final String COLSPAN = "colspan";
    public static final String CONTENT = "content";
    public static final String CONTENTEDITABLE = "contenteditable";
    public static final String CONTEXTMENU = "contextmenu";
    public static final String CONTROLS = "controls";
    public static final String COORDS = "coords";
    public static final String DATA = "data";
    public static final String DATETIME = "datetime";
    public static final String DEFAULT = "default";
    public static final String DEFER = "defer";
    public static final String DIR = "dir";
    public static final String DIRNAME = "dirname";
    public static final String DISABLED = "disabled";
    public static final String DOWNLOAD = "download";
    public static final String DRAGGABLE = "draggable";
    public static final String DROPZONE = "dropzone";
    public static final String ENCTYPE = "enctype";
    public static final String FOR = "for";
    public static final String FORM = "form";
    public static final String FORMACTION = "formaction";
    public static final String HEADERS = "headers";
    public static final String HEIGHT = "height";
    public static final String HIDDEN = "hidden";
    public static final String HIGH = "high";
    public static final String HREF = "href";
    public static final String HREFLANG = "hreflang";
    public static final String HTTP_EQUIV = "http-equiv";
    public static final String ICON = "icon";
    public static final String ID = "id";
    public static final String ISMAP = "ismap";
    public static final String ITEMPROP = "itemprop";
    public static final String KEYTYPE = "keytype";
    public static final String KIND = "kind";
    public static final String LABEL = "label";
    public static final String LANG = "lang";
    public static final String LANGUAGE = "language";
    public static final String LIST = "list";
    public static final String LOOP = "loop";
    public static final String LOW = "low";
    public static final String MANIFEST = "manifest";
    public static final String MAX = "max";
    public static final String MAXLENGTH = "maxlength";
    public static final String MEDIA = "media";
    public static final String METHOD = "method";
    public static final String MIN = "min";
    public static final String MULTIPLE = "multiple";
    public static final String NAME = "name";
    public static final String NOVALIDATE = "novalidate";
    public static final String OPEN = "open";
    public static final String OPTIMUM = "optimum";
    public static final String PATTERN = "pattern";
    public static final String PING = "ping";
    public static final String PLACEHOLDER = "placeholder";
    public static final String POSTER = "poster";
    public static final String PRELOAD = "preload";
    public static final String PUBDATE = "pubdate";
    public static final String RADIOGROUP = "radiogroup";
    public static final String READONLY = "readonly";
    public static final String REL = "rel";
    public static final String REQUIRED = "required";
    public static final String REVERSED = "reversed";
    public static final String ROLE = "role";
    public static final String ROWS = "rows";
    public static final String ROWSPAN = "rowspan";
    public static final String SANDBOX = "sandbox";
    public static final String SCOPE = "scope";
    public static final String SCOPED = "scoped";
    public static final String SEAMLESS = "seamless";
    public static final String SELECTED = "selected";
    public static final String SHAPE = "shape";
    public static final String SIZE = "size";
    public static final String SIZES = "sizes";
    public static final String SPAN = "span";
    public static final String SPELLCHECK = "spellcheck";
    public static final String SRC = "src";
    public static final String SRCDOC = "srcdoc";
    public static final String SRCLANG = "srclang";
    public static final String SRCSET = "srcset";
    public static final String START = "start";
    public static final String STEP = "step";
    public static final String STYLE = "style";
    public static final String SUMMARY = "summary";
    public static final String TABINDEX = "tabindex";
    public static final String TARGET = "target";
    public static final String TITLE = "title";
    public static final String TYPE = "type";
    public static final String USEMAP = "usemap";
    public static final String VALUE = "value";
    public static final String WIDTH = "width";
    public static final String WRAP = "wrap";

}
