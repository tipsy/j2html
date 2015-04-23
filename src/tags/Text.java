package j2html.src.tags;

import static j2html.src.HtmlEscaper.HtmlEscaper.escape;

public class Text extends Tag {

	public Text(String text) {
		super(text);
	}
	
	@Override
	public String render(){
		return escape(tag);
	}
	
	@Override
	public String toString(){
		return this.render();
	}


	
}
