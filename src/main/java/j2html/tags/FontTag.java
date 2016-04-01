package j2html.tags;

import j2html.attributes.Attr;

public class FontTag extends ContainerTag{

	public FontTag() {
		super("font");
	}

	public FontTag withFontFace(String value)                                      { return (FontTag) attr(Attr.FONT_FACE, value); }
	public FontTag withFontSize(String value)                                      { return (FontTag) attr(Attr.SIZE, value); }    
	public FontTag withFontColor(String value)                                     { return (FontTag) attr(Attr.COLOR, value); }
	
}
