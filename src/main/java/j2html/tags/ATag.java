package j2html.tags;

import j2html.attributes.Attr;

public class ATag extends ContainerTag<ATag> {

    public ATag() {
        super("a");
    }

    public ATag withCharset(String charset) {
        return attr(Attr.CHARSET, charset);
    }

    public ATag withCondCharset(boolean condition, String charset) {
        return condAttr(condition, Attr.CHARSET, charset);
    }

    public ATag withCoords(String charset) {
        return attr(Attr.COORDS, charset);
    }

    public ATag withCondCoords(boolean condition, String coords) {
        return condAttr(condition, Attr.COORDS, coords);
    }

    public ATag withDownload(String download) {
        return attr(Attr.DOWNLOAD, download);
    }

    public ATag withCondDownload(boolean condition, String download) {
        return condAttr(condition, Attr.COORDS, download);
    }

    public ATag withHref(String href) {
        return attr(Attr.HREF, href);
    }

    public ATag withCondHref(boolean condition, String href) {
        return condAttr(condition, Attr.HREF, href);
    }

    public ATag withHreflang(String hreflang) {
        return attr(Attr.HREFLANG, hreflang);
    }

    public ATag withCondHreflang(boolean condition, String hreflang) {
        return condAttr(condition, Attr.HREFLANG, hreflang);
    }

    public ATag withMedia(String media) {
        return attr(Attr.MEDIA, media);
    }

    public ATag withCondMedia(boolean condition, String media) {
        return condAttr(condition, Attr.MEDIA, media);
    }

    public ATag withName(String name) {
        return attr(Attr.NAME, name);
    }

    public ATag withCondName(boolean condition, String name) {
        return condAttr(condition, Attr.NAME, name);
    }

    public ATag withPing(String ping) {
        return attr(Attr.PING, ping);
    }

    public ATag withCondPing(boolean condition, String ping) {
        return condAttr(condition, Attr.PING, ping);
    }

    public ATag withRel(String rel) {
        return attr(Attr.REL, rel);
    }

    public ATag withCondRel(boolean condition, String rel) {
        return condAttr(condition, Attr.REL, rel);
    }

    public ATag withRev(String rev) {
        return attr(Attr.REV, rev);
    }

    public ATag withCondRev(boolean condition, String rev) {
        return condAttr(condition, Attr.REV, rev);
    }

    public ATag withShape(String shape) {
        return attr(Attr.SHAPE, shape);
    }

    public ATag withCondShape(boolean condition, String shape) {
        return condAttr(condition, Attr.SHAPE, shape);
    }

    public ATag withTarget(String target) {
        return attr(Attr.TARGET, target);
    }

    public ATag withCondTarget(boolean condition, String target) {
        return condAttr(condition, Attr.TARGET, target);
    }

    public ATag withType(String type) {
        return attr(Attr.TYPE, type);
    }

    public ATag withCondType(boolean condition, String type) {
        return condAttr(condition, Attr.TYPE, type);
    }
}
