package j2html.tags;


import static j2html.utils.SimpleHtmlEscaper.escape;

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
